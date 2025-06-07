package co.id.xiaomun.serviceappsxiaomun.model.responseThirdParty;

import lombok.Data;

import java.util.List;

@Data
public class PaymentChannelResponse {
    private boolean success;
    private String message;
    private List<PaymentChannelData> data;
}
