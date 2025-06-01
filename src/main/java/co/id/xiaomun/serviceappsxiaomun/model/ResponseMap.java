package co.id.xiaomun.serviceappsxiaomun.model;

import lombok.Data;
import java.util.Map;

@Data
public class ResponseMap {

    private boolean status;
    private String statusCode;
    private String responseCode;
    private String responseMessage;
    private Map<String, Object> result;

}
