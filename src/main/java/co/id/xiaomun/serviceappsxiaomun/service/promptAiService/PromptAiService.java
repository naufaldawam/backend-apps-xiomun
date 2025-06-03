package co.id.xiaomun.serviceappsxiaomun.service.promptAiService;

import co.id.xiaomun.serviceappsxiaomun.model.ResponseMap;
import co.id.xiaomun.serviceappsxiaomun.model.promptAiModel.PromptAiModel;

public interface PromptAiService {

    public ResponseMap askGemini(PromptAiModel request) throws Exception;
    
}
