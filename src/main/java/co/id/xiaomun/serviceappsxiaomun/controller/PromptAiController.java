package co.id.xiaomun.serviceappsxiaomun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.id.xiaomun.serviceappsxiaomun.constant.LogInfoFormat;
import co.id.xiaomun.serviceappsxiaomun.model.ResponseMap;
import co.id.xiaomun.serviceappsxiaomun.model.request.promptAiModel.PromptAiModel;
import co.id.xiaomun.serviceappsxiaomun.service.promptAiService.PromptAiService;

@RestController
@CrossOrigin("*")
public class PromptAiController {

    @Autowired
    private LogInfoFormat logInfoFormat;

    @Autowired
    private PromptAiService promptAiService;
   
    @PostMapping("/recommendation")
    public ResponseEntity<ResponseMap> getRecomendation(@RequestBody PromptAiModel request) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMap response = promptAiService.askGemini(request);

        if (response.getResponseCode().equals("00")) {
            String log = logInfoFormat.setLogInfo("recomendation menu with ai gemini",
                    objectMapper.writeValueAsString(request),
                    objectMapper.writeValueAsString(response), "/recomendation", "", "");

            System.out.println(log);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

    }


}
