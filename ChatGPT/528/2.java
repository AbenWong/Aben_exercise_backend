import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Component
public class SmsTask {
    
    @Autowired
    private FeedbackMapper feedbackMapper;
    
    @Autowired
    private MessageDataMapper messageDataMapper;
    
    private RestTemplate restTemplate = new RestTemplate();
    
    private String baseUrl = "https://api.themoviedb.org/3/movie/changes";
    private int limit = 100;

    @Scheduled(cron = "0 0 2 * * ?")
    public void executeTask() {
        List<NC_SMS_FEEDBACK> failedFeedbacks = feedbackMapper.findFailedFeedbacks();
        List<String> urls = generateUrls(failedFeedbacks);

        for (String url : urls) {
            for (int i = 0; i < 5; i++) {
                boolean success = sendGetRequest(url);
                if (success) {
                    break;
                }
            }
        }
    }

    private List<String> generateUrls(List<NC_SMS_FEEDBACK> feedbacks) {
        List<String> urls = new ArrayList<>();
        for (NC_SMS_FEEDBACK feedback : feedbacks) {
            String url = baseUrl + "?offset=" + feedback.getCurrentPaginate() + "&limit=" + limit + "&start_date=" + feedback.getDeliveryStartDate() + "&end_date=" + feedback.getDeliveryEndDate();
            urls.add(url);
        }
        return urls;
    }

    private boolean sendGetRequest(String url) {
        try {
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
            JsonNode body = response.getBody();
            if (body != null) {
                processResponse(body);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void processResponse(JsonNode body) {
        JsonNode data = body.get("data");
        JsonNode meta = body.get("meta");
        if (data != null && meta != null) {
            JsonNode contactRecords = data.get("contactRecords");
            JsonNode pagination = meta.get("pagination");
            int totalRecordCount = pagination.get("totalRecordCount").asInt();
            boolean moreRecordsIndicator = pagination.get("moreRecordsIndicator").asBoolean();

            for (JsonNode record : contactRecords) {
                NC_SMS_MESSAGE_DATA messageData = new NC_SMS_MESSAGE_DATA();
                messageData.setDeliveryDateTime(Timestamp.valueOf(record.get("devliveryDateTime").asText()));
                messageData.setTemplateId(record.get("templateCode").asText());
                messageData.setTemplateIdContentText(record.get("contentText").asText());
                messageData.setDeliverStatusCode(record.get("deliveryStatusCode").asText());
                messageData.setDeliveryStatusDescription(record.get("deliveryStatusDescription").asText());
                messageData.setCustomerIdentificationNumber(record.get("customerIdentificationNumber").asText());
                messageData.setMobilePhoneNumber(record.get("mobilephoneNumber").asText());
                messageData.setSourceSystemId(record.get("evenTriggeredSystemShortName").asText());
                messageData.setTrackingNumber(record.get("messageUuidText").asText());
                messageDataMapper.insertMessageData(messageData);
            }

            NC_SMS_FEEDBACK feedback = new NC_SMS_FEEDBACK();
            feedback.setCurrentPaginate(offset);
            feedback.setDeliveryStartDate(java.sql.Date.valueOf(startDate));
            feedback.setDeliveryEndDate(java.sql.Date.valueOf(endDate));
            feedback.setCreateTime(createTime);
            feedback.setLastUpdateTime(Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
            feedback.setReloadCount(reload);
            feedback.setCurrentStatus("1");
            feedback.setLastFailReason(null);
            feedback.setTotalRecordCode(totalRecordCount);
            feedback.setMoreRecordsIndicator(moreRecordsIndicator ? "1" : "0");
            feedbackMapper.insertFeedback(feedback);
        }
    }
}
