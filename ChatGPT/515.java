import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GetRequestsApplication {

    private static final Logger logger = LoggerFactory.getLogger(GetRequestsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GetRequestsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public void sendMultipleGetRequests(RestTemplate restTemplate) {
        String baseUrl = "https://example.com/api"; // 要发送请求的基础 URL
        String AURL = "kk.com";
        String SDate = "2024-11-12";
        int offset = 0;
        int limit = 25;
        int retryAttempts = 5;
        int retryCount = 0;

        boolean more = true;
        while (more) {
            String url = baseUrl + "?AURL=" + AURL + "&SDate=" + SDate + "&offset=" + offset + "&limit=" + limit;

            try {
                ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
                String responseBody = responseEntity.getBody();
                System.out.println("Response Body: " + responseBody);

                // 解析返回的 JSON，获取 more 的值
                // 这里假设返回的是 JSON，实际情况可能需要使用 JSON 库进行解析
                more = parseMoreValueFromResponse(responseBody);
                if (more) {
                    offset += limit;
                }
            } catch (Exception e) {
                // 请求失败，尝试重新发送请求
                retryCount++;
                if (retryCount <= retryAttempts) {
                    logger.error("Error occurred while sending GET request, retrying (attempt {})", retryCount);
                } else {
                    logger.error("Failed to send GET request after {} attempts, current state is not available", retryAttempts);
                    break;
                }
            }
        }
    }

    // 解析返回结果中的 more 字段
    private boolean parseMoreValueFromResponse(String responseBody) {
        // 这里假设 responseBody 是 JSON 格式的字符串，实际情况可能需要使用 JSON 库进行解析
        // 假设 responseBody 中有一个名为 "more" 的字段
        // 这里只是一个示例，实际情况需要根据返回的具体格式进行解析
        // 如果 responseBody 中包含 more 字段，可以通过相应的逻辑进行解析并返回其布尔值
        return true; // 假设始终返回 true
    }
}
