@Service
public class MovieChangesService {

    private final RestTemplate restTemplate;
    private final NcSmsFeedbackMapper feedbackMapper;
    private final NcSmsMessageDataMapper messageDataMapper;

    @Autowired
    public MovieChangesService(RestTemplateBuilder restTemplateBuilder, NcSmsFeedbackMapper feedbackMapper, NcSmsMessageDataMapper messageDataMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.feedbackMapper = feedbackMapper;
        this.messageDataMapper = messageDataMapper;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void fetchMovieChanges() {
        String baseUrl = "https://api.themoviedb.org/3/movie/changes";
        int offset = 0;
        int limit = 100;
        LocalDate startDate = LocalDate.now().minusDays(1);
        LocalDate endDate = LocalDate.now().minusDays(1);
        boolean moreRecordsIndicator = true;

        while (moreRecordsIndicator) {
            try {
                String url = String.format("%s?offset=%d&limit=%d&start_date=%s&end_date=%s", baseUrl, offset, limit, startDate, endDate);
                ResponseEntity<Map<String, Object>> response = restTemplate.getForEntity(url, Map.class);

                Map<String, Object> data = (Map<String, Object>) response.getBody().get("data");
                Map<String, Object> meta = (Map<String, Object>) response.getBody().get("meta");
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
                feedback.setDeliveryStartDate(Date.valueOf(startDate));
                feedback.setDeliveryEndDate(Date.valueOf(endDate));
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
                feedback.setDeliveryStartDate(Date.valueOf(startDate));
                feedback.setDeliveryEndDate(Date.valueOf(endDate));
                feedback.setCreateTime(new Timestamp(System.currentTimeMillis()));
                feedback.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
                feedback.setReloadCount(1);  // 初始为1，稍后会递增
                feedback.setCurrentStatus('0');
                feedback.setLastFailReason(e.getMessage());
                feedback.setTotalRecordCode(null);
                feedback.setMoreRecordsIndicator('0');

                feedbackMapper.insertNcSmsFeedback(feedback);

                log.error("本次请求失败", e);

                int reloadCount = 1;

                while (reloadCount <= 30) {
                    try {
                        ResponseEntity<Map<String, Object>> retryResponse = restTemplate.getForEntity(url, Map.class);
                        // 如果成功，继续下一步处理...
                        break;  // 成功后退出重试循环
                    } catch (Exception retryException) {
                        feedback.setReloadCount(reloadCount++);
                        feedback.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
                        feedback.setLastFailReason(retryException.getMessage());
                        feedbackMapper.updateNcSmsFeedback(feedback);
                    }
                }

                if (reloadCount > 30) {
                    log.error("请求失败，已超过最大重试次数");
                }

                break;  // 跳出外层while循环，停止请求
            }
        }
    }
}
