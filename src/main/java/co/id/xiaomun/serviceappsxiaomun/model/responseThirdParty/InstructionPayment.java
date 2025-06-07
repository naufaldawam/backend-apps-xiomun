package co.id.xiaomun.serviceappsxiaomun.model.responseThirdParty;

import lombok.Data;
import java.util.List;

@Data
public class InstructionPayment {
    private String title;
    private List<String> steps;

}
