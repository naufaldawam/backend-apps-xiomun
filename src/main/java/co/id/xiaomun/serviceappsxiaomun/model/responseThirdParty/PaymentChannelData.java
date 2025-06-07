package co.id.xiaomun.serviceappsxiaomun.model.responseThirdParty;

import lombok.Data;
import java.util.List;

@Data
public class PaymentChannelData {
    private int group_id;
    private String group_name;
    private List<PaymentData> payment;
}
