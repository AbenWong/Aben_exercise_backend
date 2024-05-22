import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class MovieChangesService {

    private final RestTemplate restTemplate;
    private final NcSmsFeedbackMapper feedbackMapper;
    private final NcSmsMessageDataMapper messageDataMapper;

    @Autowired
    public MovieChangesService(RestTemplate restTemplate, NcSmsFeedbackMapper feedbackMapper, NcSmsMessageDataMapper messageDataMapper) {
        this.restTemplate = restTemplate;
        this.feedbackMapper = feedbackMapper;
        this.messageDataMapper = messageDataMapper;
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void fetchMovieChanges() {
        String baseUrl = "https://api.themoviedb.org/3/movie/changes";
        int offset = 0;
        int limit = 100;
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now().minusDays(1);
        boolean moreRecordsIndicator = true;

        while (moreRecordsIndicator) {
            String url = String.format("%s?offset=%d&limit=%d&start_date=%s&end_date=%s", baseUrl, offset, limit, startDate, endDate);
            try {
                Map<String, Object> response = restTemplate.getForObject(url, Map.class);

                Map<String, Object> data = (Map<String, Object>) response.get("data");
                Map<String, Object> meta = (Map<String, Object>) response.get("meta");
                Map<String, Object> pagination = (Map<String, Object>) meta.get("pagination");

                List<Map<String, Object>> contactRecords = (List<Map<String, Object>>) data.get("contactRecords");
                moreRecordsIndicator = (Boolean) pagination.get("moreRecordsIndicator");
                int totalRecordCount = (Integer) pagination.get("totalRecordCount");

                Timestamp currentTime = new Timestamp(System.currentTimeMillis());

                for (Map<String, Object> record : contactRecords) {
                    NcSmsMessageData messageData = new NcSmsMessageData();
                    messageData.setDeliveryDateTime(Timestamp.valueOf(record.get("devliveryDateTime").toString()));
                    messageData.setTemplateId(record.get("templateCode").toString());
                    messageData.setTemplateIdContentText(record.get("contentText").toString());
                    messageData.setDeliverStatusCode(record.get("deliveryStatusCode").toString());
                    messageData.setDeliveryStatusDescription(record.get("deliveryStatusDescription").toString());
                    messageData.setCustomerIdentificationNumber(record.get("customerIdentificationNumber").toString());
                    messageData.setMobilePhoneNumber(record.get("mobilephoneNumber").toString());
                    messageData.setSourceSystemId(record.get("evenTriggeredSystemShortName").toString());
                    messageData.setTrackingNumber(record.get("messageUuidText").toString());

                    messageDataMapper.insertNcSmsMessageData(messageData);
                }

                NcSmsFeedback feedback = new NcSmsFeedback();
                feedback.setCurrentPaginate(offset);
                feedback.setDeliveryStartDate(java.sql.Date.valueOf(startDate));
                feedback.setDeliveryEndDate(java.sql.Date.valueOf(endDate));
                feedback.setCreateTime(currentTime);
                feedback.setLastUpdateTime(currentTime);
                feedback.setReloadCount(0);
                feedback.setCurrentStatus('1');
                feedback.setLastFailReason(null);
                feedback.setTotalRecordCode(totalRecordCount);
                feedback.setMoreRecordsIndicator('1');

                feedbackMapper.insertNcSmsFeedback(feedback);

                offset += limit;

            } catch (Exception e) {
                NcSmsFeedback feedback = new NcSmsFeedback();
                feedback.setCurrentPaginate(offset);
                feedback.setDeliveryStartDate(java.sql.Date.valueOf(startDate));
                feedback.setDeliveryEndDate(java.sql.Date.valueOf(endDate));
                feedback.setCreateTime(new Timestamp(System.currentTimeMillis()));
                feedback.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
                feedback.setReloadCount(0);  // 重试计数器初始化
                feedback.setCurrentStatus('0');
                feedback.setLastFailReason(e.getMessage());
                feedback.setTotalRecordCode(null);
                feedback.setMoreRecordsIndicator('0');

                feedbackMapper.insertNcSmsFeedback(feedback);

                log.error("本次请求失败", e);

                break;  // 跳出外层while循环，停止请求
            }
        }
    }
}
