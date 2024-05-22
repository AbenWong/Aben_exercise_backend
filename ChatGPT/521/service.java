import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class MovieChangeService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NC_SMS_FEEDBACKMapper feedbackMapper;

    @Autowired
    private NC_SMS_MESSAGE_DATAMapper messageDataMapper;

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/changes";
    private static final int LIMIT = 1000;
    private static final int MAX_RELOAD_COUNT = 30;

    public void sendMultipleGetRequests() {
        List<NC_SMS_FEEDBACK> pendingRecords = feedbackMapper.selectPendingRecords();
        for (NC_SMS_FEEDBACK record : pendingRecords) {
            processRecord(record);
        }
    }

    private void processRecord(NC_SMS_FEEDBACK record) {
        boolean moreRecords = true;
        int offset = record.getCurrentPaginate();
        int reloadCount = record.getReloadCount();

        while (moreRecords) {
            String url = String.format("%s?start_date=%s&end_date=%s&offset=%d&limit=%d",
                    BASE_URL,
                    record.getDeliveryStartDate(),
                    record.getDeliveryEndDate(),
                    offset,
                    LIMIT);

            try {
                ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url, Map.class);
                Map<String, Object> responseBody = responseEntity.getBody();
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");
                Map<String, Object> meta = (Map<String, Object>) responseBody.get("meta");
                Map<String, Object> pagination = (Map<String, Object>) meta.get("pagination");
                List<Map<String, Object>> contactRecords = (List<Map<String, Object>>) data.get("contactRecords");

                for (Map<String, Object> contactRecord : contactRecords) {
                    NC_SMS_MESSAGE_DATA messageData = new NC_SMS_MESSAGE_DATA();
                    messageData.setDeliveryDateTime(Timestamp.valueOf((String) contactRecord.get("devliveryDateTime")));
                    messageData.setTemplateId((String) contactRecord.get("templateCode"));
                    messageData.setContentText((String) contactRecord.get("contentText"));
                    messageData.setDeliveryStatusCode((String) contactRecord.get("deliveryStatusCode"));
                    messageData.setDeliveryStatusDescription((String) contactRecord.get("deliveryStatusDescription"));
                    messageData.setCustomerIdentificationNumber((String) contactRecord.get("customerIdentificationNumber"));
                    messageData.setMobilePhoneNumber((String) contactRecord.get("mobilephoneNumber"));
                    messageData.setSourceSystemId((String) contactRecord.get("evenTriggeredSystemShortName"));
                    messageData.setTrackingNumber((String) contactRecord.get("messageUuidText"));
                    messageDataMapper.insertMessageData(messageData);
                }

                moreRecords = (Boolean) pagination.get("moreRecordsIndicator");
                if (moreRecords) {
                    offset += LIMIT;
                }

                record.setCurrentPaginate(offset);
                record.setReloadCount(reloadCount);
                record.setCurrentStatus("1");
                record.setLastFailReason(null);
                record.setTotalRecordCode((Integer) pagination.get("totalRecordCount"));
                record.setMoreRecordsIndicator("1");
                record.setLastUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
                feedbackMapper.updateFeedback(record);

            } catch (Exception e) {
                handleRequestError(record, e.getMessage());
                break;
            }
        }
    }

    private void handleRequestError(NC_SMS_FEEDBACK record, String errorMessage) {
        record.setCurrentStatus("0");
        record.setLastFailReason(errorMessage);
        record.setLastUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        record.setReloadCount(record.getReloadCount() + 1);
        if (record.getReloadCount() >= MAX_RELOAD_COUNT) {
            System.out.printf("Request failed after %d retries for startDate: %s, endDate: %s, paginate: %d%n",
                    record.getReloadCount(), record.getDeliveryStartDate(), record.getDeliveryEndDate(), record.getCurrentPaginate());
        }
        feedbackMapper.updateFeedback(record);
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void dailyScanTask() {
        sendMultipleGetRequests();
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void retryFailedRequests() {
        List<NC_SMS_FEEDBACK> failedRecords = feedbackMapper.selectPendingRecords();
        for (NC_SMS_FEEDBACK record : failedRecords) {
            if (record.getReloadCount() > MAX_RELOAD_COUNT) {
                System.out.printf("Request permanently failed for startDate: %s, endDate: %s, paginate: %d%n",
                        record.getDeliveryStartDate(), record.getDeliveryEndDate(), record.getCurrentPaginate());
            }
        }
    }
}
