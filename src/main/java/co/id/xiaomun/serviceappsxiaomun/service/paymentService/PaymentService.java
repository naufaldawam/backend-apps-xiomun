package co.id.xiaomun.serviceappsxiaomun.service.paymentService;

import co.id.xiaomun.serviceappsxiaomun.model.ResponseMap;
import co.id.xiaomun.serviceappsxiaomun.model.paymentModel.PaymentRequest;

public interface PaymentService {
     public ResponseMap processPayment(PaymentRequest request)throws Exception;
}
