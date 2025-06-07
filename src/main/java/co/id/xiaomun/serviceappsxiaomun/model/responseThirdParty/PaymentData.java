package co.id.xiaomun.serviceappsxiaomun.model.responseThirdParty;

import lombok.Data;
import java.util.List;

@Data
public class PaymentData {
    
  private String code;
    private String name;
    private String type;
    private String description;
    private List<InstructionPayment> instructions;
    private FeePayment fee;
    private Object minimum_fee; // bisa diganti BigDecimal jika yakin numerik
    private Object maximum_fee;
}
