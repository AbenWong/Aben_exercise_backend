import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/changes";
    private static final int LIMIT = 1000;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NcSmsFeedbackMapper feedbackMapper;

    @Autowired
    private NcSmsMessageDataMapper messageDataMapper;

    @Scheduled(cron = "0 0 1 * * ?")
    public void scanAndProcessFeedback() {
        List<NcSmsFeedback> pendingFeedbacks = feedbackMapper.findPendingFeedbacks();

        for (NcSmsFeedback feedback : pendingFeedbacks) {
            processFeedback(feedback);
        }
    }

    private void processFeedback(NcSmsFeedback feedback) {
        int offset = feedback.getCurrentPaginate();
        boolean moreRecordsIndicator = true;
        Date startDate = feedback.getDeliveryStartDate();
        Date endDate = feedback.getDeliveryEndDate();

        while (moreRecordsIndicator && feedback.getReloadCount() < 30) {
            try {
                String url = buildUrl(startDate, endDate, offset, LIMIT);
                ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);

                if (response != null && response.getMeta() != null && response.getMeta().getPagination() != null) {
                    processResponse(response, feedback, offset);
                    moreRecordsIndicator = response.getMeta().getPagination().isMoreRecordsIndicator();
                    offset += LIMIT;
                } else {
                    handleFailedRequest(feedback, "Invalid response structure");
                    break;
                }
            } catch (Exception e) {
                handleFailedRequest(feedback, e.getMessage());
            }
        }
    }

    private void processResponse(ApiResponse response, NcSmsFeedback feedback, int offset) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        feedback.setCurrentPaginate(offset);
        feedback.setCreateTime(now);
        feedback.setLastUpdateTime(now);
        feedback.setReloadCount(feedback.getReloadCount() + 1);
        feedback.setCurrentStatus("1");
        feedback.setLastFailReason(null);
        feedback.setTotalRecordCode(response.getMeta().getPagination().getTotalRecordCount());
        feedback.setMoreRecordsIndicator(response.getMeta().getPagination().isMoreRecordsIndicator() ? "1" : "0");

        feedbackMapper.updateFeedback(feedback);

        for (ContactRecord record : response.getData().getContactRecords()) {
            NcSmsMessageData messageData = new NcSmsMessageData();
            messageData.setDeliveryDateTime(Timestamp.valueOf(record.getDevliveryDateTime()));
            messageData.setTemplateId(record.getTemplateCode());
            messageData.setContentText(record.getContentText());
            messageData.setDeliverStatusCode(record.getDeliveryStatusCode());
            messageData.setDeliveryStatusDescription(record.getDeliveryStatusDescription());
            messageData.setCustomerIdentificationNumber(record.getCustomerIdentificationNumber());
            messageData.setMobilePhoneNumber(record.getMobilephoneNumber());
            messageData.setSourceSystemId(record.getEvenTriggeredSystemShortName());
            messageData.setTrackingNumber(record.getMessageUuidText());

            messageDataMapper.insertMessageData(messageData);
        }
    }

    private void handleFailedRequest(NcSmsFeedback feedback, String errorReason) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        feedback.setCurrentStatus("0");
        feedback.setLastFailReason(errorReason);
        feedback.setLastUpdateTime(now);
        feedback.setReloadCount(feedback.getReloadCount() + 1);

        feedbackMapper.updateFeedback(feedback);
    }

    private String buildUrl(Date startDate, Date endDate, int offset, int limit) {
        String startDateStr = dateFormat.format(startDate);
        String endDateStr = dateFormat.format(endDate);
        return String.format("%s?start_date=%s&end_date=%s&offset=%d&limit=%d", BASE_URL, startDateStr, endDateStr, offset, limit);
    }
}
