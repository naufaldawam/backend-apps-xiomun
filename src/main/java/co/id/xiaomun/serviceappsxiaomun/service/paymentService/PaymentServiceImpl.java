package co.id.xiaomun.serviceappsxiaomun.service.paymentService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.xiaomun.serviceappsxiaomun.entity.payment.Payment;
import co.id.xiaomun.serviceappsxiaomun.model.ResponseMap;
import co.id.xiaomun.serviceappsxiaomun.model.paymentModel.PaymentRequest;
import co.id.xiaomun.serviceappsxiaomun.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public ResponseMap processPayment(PaymentRequest request)throws Exception{
       ResponseMap response = new ResponseMap();

        // 1. Simpan transaksi ke database
        Payment dataPayment  = new Payment();
        dataPayment.setOrderId(request.getOrderId());
        dataPayment.setCustomerName(request.getCustomerName());
        dataPayment.setPaymentMethod(request.getPaymentMethod());
        dataPayment.setAmount(request.getAmount());
        dataPayment.setStatus("pending");
        dataPayment.setTransactionDate(generateLocalTransactionDate());

        Payment savedTransaction = paymentRepository.save(dataPayment);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("transaction", savedTransaction);

        response.setStatus(true);
        response.setStatusCode("200");
        response.setResponseCode("00");
        response.setResponseMessage("Success process payment");
        response.setResult(resultMap);

        return response;
    }

    public String generateLocalTransactionDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd");
        return now.format(formatter);
    }
}
