package moanote.backend.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@RestController
@RequestMapping("/api") // 엔드포인트 경로 정의
public class OpenAIController {

    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private final String OPENAI_API_KEY = "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";

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