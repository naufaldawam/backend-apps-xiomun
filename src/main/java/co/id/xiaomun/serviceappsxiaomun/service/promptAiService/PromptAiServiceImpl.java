package co.id.xiaomun.serviceappsxiaomun.service.promptAiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.id.xiaomun.serviceappsxiaomun.entity.menu.MenuItem;
import co.id.xiaomun.serviceappsxiaomun.model.ResponseMap;
import co.id.xiaomun.serviceappsxiaomun.model.promptAiModel.PromptAiModel;
import co.id.xiaomun.serviceappsxiaomun.repository.MenuRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromptAiServiceImpl implements PromptAiService {

    @Autowired
    private MenuRepository menuRepository;

    @Value("${spring.ai.gemini.api-key}")
    private String apiKey;

    @Value("${spring.ai.gemini.url}")
    private String GEMINI_URL_TEMPLATE;

    @Override
    public ResponseMap askGemini(PromptAiModel request) throws Exception {
        ResponseMap response = new ResponseMap();

        try {
            List<MenuItem> allMenus = menuRepository.findAll();
            String userPreference = request.getPreference();

            String prompt = buildPrompt(allMenus, userPreference);
            String aiResult = sendToModelAi(prompt);

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("recommendation", aiResult);

            response.setStatus(true);
            response.setStatusCode("success");
            response.setResponseCode("00");
            response.setResponseMessage("Recommendation retrieved successfully.");
            response.setResult(resultMap);
        } catch (Exception e) {
            response.setStatus(false);
            response.setStatusCode("500 - failed internal server error");
            response.setResponseCode("99");
            response.setResponseMessage("Failed to process recommendation: " + e.getMessage());
            response.setResult(null);
        }

        return response;
    }

    private String buildPrompt(List<MenuItem> menus, String userPreference) {
        String menuList = menus.stream()
                .map(menu -> String.format("%s (Rp %,d)", menu.getName(), menu.getPrice()))
                .collect(Collectors.joining(", "));

        return "Berikut adalah daftar menu: " + menuList +
               ". Rekomendasikan 3 menu yang cocok untuk preferensi pelanggan: " + userPreference;
    }

    private String sendToModelAi(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = Map.of(
            "contents", List.of(
                Map.of("parts", List.of(Map.of("text", prompt)))
            )
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        String url = String.format(GEMINI_URL_TEMPLATE, apiKey);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");

                if (candidates != null && !candidates.isEmpty()) {
                    Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                    List<Map<String, String>> parts = (List<Map<String, String>>) content.get("parts");

                    if (parts != null && !parts.isEmpty()) {
                        return parts.get(0).get("text");
                    }
                }
            }

            return "Tidak ada hasil dari Gemini.";
        } catch (Exception e) {
            return "Gagal mendapatkan rekomendasi: " + e.getMessage();
        }
    }
}
