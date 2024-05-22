import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasks {

    @Value("${base.url}")
    private String baseUrl;

    private String offset = "0";
    private final String limit = "100";
    private String start_date;
    private String end_date;
    private boolean moreRecordsIndicator = true;
    private int reloadCount = 0;

    @Scheduled(fixedRate = 5000) // Run every 5 seconds
    public void fetchDataAndSave() {
        if (moreRecordsIndicator && reloadCount <= 30) {
            String url = constructUrl();
            RestTemplate restTemplate = new RestTemplate();
            try {
                ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);
                if (response != null && response.getMeta() != null && response.getMeta().getPagination() != null) {
                    Pagination pagination = response.getMeta().getPagination();
                    moreRecordsIndicator = pagination.isMoreRecordsIndicator();
                    int totalRecordCount = pagination.getTotalRecordCount();
                    // Save data to database
                    saveData(response.getData());
                    // Update offset for next request
                    offset = String.valueOf(Integer.parseInt(offset) + Integer.parseInt(limit));
                    reloadCount = 0; // Reset reload count
                } else {
                    handleFailure("Invalid response format");
                }
            } catch (Exception e) {
                handleFailure(e.getMessage());
            }
        } else {
            // Stop the scheduled task
            // or log that no more records to fetch
        }
    }

    private String constructUrl() {
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        start_date = yesterday.format(DateTimeFormatter.ISO_DATE);
        end_date = yesterday.format(DateTimeFormatter.ISO_DATE);
        return baseUrl + "?offset=" + offset + "&limit=" + limit +
                "&start_date=" + start_date + "&end_date=" + end_date;
    }

    private void saveData(Data data) {
        // Save data to NC_SMS_FEEDBACK and NC_SMS_MESSAGE_DATA tables using MyBatis
        // ...
    }

    private void handleFailure(String reason) {
        // Log the failure reason
        System.out.println("Request failed: " + reason);
        // Update status in database
        // ...
        // Retry the failed request if reload count is less than 30
        if (reloadCount < 30) {
            reloadCount++;
            // Wait for some time before retrying, if necessary
            // ...
            fetchDataAndSave(); // Retry the request
        }
    }
}
