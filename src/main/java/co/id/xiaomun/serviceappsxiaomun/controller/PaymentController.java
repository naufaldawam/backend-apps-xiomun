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
import co.id.xiaomun.serviceappsxiaomun.model.request.paymentModel.GetPaymentChannel;
import co.id.xiaomun.serviceappsxiaomun.model.request.paymentModel.GetPaymentChannelMerchant;
import co.id.xiaomun.serviceappsxiaomun.model.request.paymentModel.PaymentRequest;
import co.id.xiaomun.serviceappsxiaomun.service.paymentService.PaymentService;

@RestController
@CrossOrigin("*")
public class PaymentController {
    @Autowired
    private LogInfoFormat logInfoFormat;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<ResponseMap> processPayment(@RequestBody PaymentRequest request) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMap response = paymentService.processPayment(request);

        if (response.getResponseCode().equals("00")) {
            String log = logInfoFormat.setLogInfo("Access payment",
                    objectMapper.writeValueAsString(request),
                    objectMapper.writeValueAsString(response), "/payment", "", "");

            System.out.println(log);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/getPaymentChannelAll")
    public ResponseEntity<ResponseMap> getPaymentChannel(@RequestBody GetPaymentChannel request) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMap response = paymentService.getPaymentChannel(request);

        if (response.getResponseCode().equals("00")) {
            String log = logInfoFormat.setLogInfo("Access payment",
                    objectMapper.writeValueAsString(request),
                    objectMapper.writeValueAsString(response), "/payment", "", "");

            System.out.println(log);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/getPaymentChannelMerchant")
    public ResponseEntity<ResponseMap> getPaymentChannelMerchant(@RequestBody GetPaymentChannelMerchant request) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseMap response = paymentService.getPaymentChannelMerchant(request);

        if (response.getResponseCode().equals("00")) {
            String log = logInfoFormat.setLogInfo("Access payment",
                    objectMapper.writeValueAsString(request),
                    objectMapper.writeValueAsString(response), "/payment", "", "");

            System.out.println(log);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
    }

}
