package co.id.xiaomun.serviceappsxiaomun.service.paymentService;

import co.id.xiaomun.serviceappsxiaomun.model.ResponseMap;
import co.id.xiaomun.serviceappsxiaomun.model.request.paymentModel.GetPaymentChannel;
import co.id.xiaomun.serviceappsxiaomun.model.request.paymentModel.GetPaymentChannelMerchant;
import co.id.xiaomun.serviceappsxiaomun.model.request.paymentModel.PaymentRequest;

public interface PaymentService {
     public ResponseMap processPayment(PaymentRequest request)throws Exception;
     public ResponseMap getPaymentChannel(GetPaymentChannel request)throws Exception;
     public ResponseMap getPaymentChannelMerchant(GetPaymentChannelMerchant request) throws Exception;
}
