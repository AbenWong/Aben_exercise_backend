import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class MyScheduledTask {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NcSmsFeedbackMapper feedbackMapper;

    @Autowired
    private NcSmsMessageDataMapper messageDataMapper;

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/changes";
    private static final int LIMIT = 100;
    private static final int RELOAD_THRESHOLD = 30;

    private int offset = 0;
    private boolean moreRecordsIndicator = true;
    private int reload = 0;

    @Scheduled(cron = "0 0 2 * * ?") // 每天凌晨2点
    public void performTask() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        Date startDate = Date.from(yesterday.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(yesterday.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        while (moreRecordsIndicator) {
            try {
                String url = String.format("%s?offset=%d&limit=%d&start_date=%s&end_date=%s",
                        BASE_URL, offset, LIMIT, startDate, endDate);
                ResponseEntity<ApiResponse> response = restTemplate.getForEntity(url, ApiResponse.class);

                ApiResponse responseBody = response.getBody();
                if (responseBody != null) {
                    handleResponse(responseBody, startDate, endDate);
                }
                offset += LIMIT;
            } catch (Exception e) {
                handleFailure(startDate, endDate, e.getMessage());
                if (reload >= RELOAD_THRESHOLD) {
                    break;
                }
                reload++;
            }
        }
    }

    private void handleResponse(ApiResponse responseBody, Date startDate, Date endDate) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        for (ContactRecord record : responseBody.getData().getContactRecords()) {
            NcSmsMessageData messageData = new NcSmsMessageData();
            messageData.setDeliveryDateTime(Timestamp.valueOf(record.getDeliveryDateTime()));
            messageData.setTemplateId(record.getTemplateCode());
            messageData.setTemplateIdContentText(record.getContentText());
            messageData.setDeliverStatusCode(record.getDeliveryStatusCode());
            messageData.setDeliveryStatusDescription(record.getDeliveryStatusDescription());
            messageData.setCustomerIdentificationNumber(record.getCustomerIdentificationNumber());
            messageData.setMobilePhoneNumber(record.getMobilephoneNumber());
            messageData.setSourceSystemId(record.getEvenTriggeredSystemShortName());
            messageData.setTrackingNumber(record.getMessageUuidText());
            messageDataMapper.insert(messageData);
        }

        NcSmsFeedback feedback = new NcSmsFeedback();
        feedback.setCurrentPaginate(offset);
        feedback.setDeliveryStartDate(startDate);
        feedback.setDeliveryEndDate(endDate);
        feedback.setCreateTime(now);
        feedback.setLastUpdateTime(now);
        feedback.setReloadCount(reload);
        feedback.setCurrentStatus("1");
        feedback.setLastFailReason(null);
        feedback.setTotalRecordCode(responseBody.getMeta().getPagination().getTotalRecordCount());
        feedback.setMoreRecordsIndicator(responseBody.getMeta().getPagination().isMoreRecordsIndicator() ? "1" : "0");
        feedbackMapper.insert(feedback);

        moreRecordsIndicator = responseBody.getMeta().getPagination().isMoreRecordsIndicator();
    }

    private void handleFailure(Date startDate, Date endDate, String errorMessage) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        NcSmsFeedback feedback = new NcSmsFeedback();
        feedback.setCurrentPaginate(offset);
        feedback.setDeliveryStartDate(startDate);
        feedback.setDeliveryEndDate(endDate);
        feedback.setCreateTime(now);
        feedback.setLastUpdateTime(now);
        feedback.setCurrentStatus("0");
        feedback.setLastFailReason(reload == 0 ? "222" : "333");
        feedback.setTotalRecordCode(null);
        feedback.setMoreRecordsIndicator("0");
        feedbackMapper.updateReloadCount(reload, now, feedback.getLastFailReason());
    }

    private static class ApiResponse {
        private Data data;
        private Meta meta;

        // Getters and setters...

        static class Data {
            private List<ContactRecord> contactRecords;

            // Getters and setters...
        }

        static class Meta {
            private Pagination pagination;

            // Getters and setters...
        }

        static class Pagination {
            private int totalRecordCount;
            private boolean moreRecordsIndicator;

            // Getters and setters...
        }

        static class ContactRecord {
            private String contactChannelType;
            private String contentText;
            private String deliveryDateTime;
            private String mobilephoneNumber;
            private String templateCode;
            private String deliveryStatusCode;
            private String deliveryStatusDescription;
            private String messageUuidText;
            private String evenTriggeredSystemShortName;
            private String customerIdentificationNumber;

            // Getters and setters...
        }
    }
}
