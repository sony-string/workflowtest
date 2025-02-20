package moanote.backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@RestController
@RequestMapping("/api") // 엔드포인트 경로 정의
public class OpenAIController {
    @Value("${openai.api.url}")
    private String OPENAI_API_URL;

    @Value("${openai.api.key}")
    private String OPENAI_API_KEY;

    @PostMapping("/proxy")
    public ResponseEntity<?> proxy(@RequestBody Map<String, Object> requestData) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            // 요청 데이터 구성
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + OPENAI_API_KEY);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestData, headers);

            // OpenAI API로 요청 전송
            ResponseEntity<String> response = restTemplate.exchange(
                    OPENAI_API_URL, HttpMethod.POST, entity, String.class
            );

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Server Error", "details", e.getMessage()));
        }
    }
}