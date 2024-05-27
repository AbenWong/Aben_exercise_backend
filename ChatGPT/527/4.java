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

@Component
public class SmsTask {
    
    @Autowired
    private FeedbackMapper feedbackMapper;
    
    @Autowired
    private MessageDataMapper messageDataMapper;
    
    private RestTemplate restTemplate = new RestTemplate();
    
    private String baseUrl = "https://api.themoviedb.org/3/movie/changes";
    private int offset = 0;
    private int limit = 100;
    private boolean moreRecordsIndicator = true;
    private int reload = 0;

    @Scheduled(cron = "0 0 2 * * ?")
    public void executeTask() {
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now().minusDays(1);
        Timestamp createTime = Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        while (moreRecordsIndicator) {
            try {
                String url = baseUrl + "?offset=" + offset + "&limit=" + limit + "&start_date=" + startDate + "&end_date=" + endDate;
                ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
                JsonNode body = response.getBody();
                if (body != null) {
                    processResponse(body, startDate, endDate, createTime);
                }
            } catch (Exception e) {
                handleFailure(startDate, endDate, createTime, e);
                if (reload > 0 && reload < 30) {
                    reload++;
                    continue;
                }
                break;
            }
        }
    }

    private void processResponse(JsonNode body, LocalDate startDate, LocalDate endDate, Timestamp createTime) {
        JsonNode data = body.get("data");
        JsonNode meta = body.get("meta");
        if (data != null && meta != null) {
            JsonNode contactRecords = data.get("contactRecords");
            JsonNode pagination = meta.get("pagination");
            int totalRecordCount = pagination.get("totalRecordCount").asInt();
            moreRecordsIndicator = pagination.get("moreRecordsIndicator").asBoolean();

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
            feedback.setMoreRecordsIndicator("1");
            feedbackMapper.insertFeedback(feedback);

            offset += limit;
        }
    }

    private void handleFailure(LocalDate startDate, LocalDate endDate, Timestamp createTime, Exception e) {
        NC_SMS_FEEDBACK feedback = new NC_SMS_FEEDBACK();
        feedback.setCurrentPaginate(offset);
        feedback.setDeliveryStartDate(java.sql.Date.valueOf(startDate));
        feedback.setDeliveryEndDate(java.sql.Date.valueOf(endDate));
        feedback.setCreateTime(createTime);
        feedback.setLastUpdateTime(Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        feedback.setReloadCount(reload);
        feedback.setCurrentStatus("0");
        feedback.setLastFailReason("222");
        feedback.setTotalRecordCode(null);
        feedback.setMoreRecordsIndicator("0");
        feedbackMapper.insertFeedback(feedback);

        if (reload == 0) {
            reload++;
        } else if (reload > 0 && reload < 30) {
            feedbackMapper.updateFeedbackFailure(offset, java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)), "333");
            reload++;
        }
    }
}
