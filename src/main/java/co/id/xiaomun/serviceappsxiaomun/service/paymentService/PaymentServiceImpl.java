package co.id.xiaomun.serviceappsxiaomun.service.paymentService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.id.xiaomun.serviceappsxiaomun.entity.payment.Payment;
import co.id.xiaomun.serviceappsxiaomun.model.ResponseMap;
import co.id.xiaomun.serviceappsxiaomun.model.request.paymentModel.GetPaymentChannel;
import co.id.xiaomun.serviceappsxiaomun.model.request.paymentModel.GetPaymentChannelMerchant;
import co.id.xiaomun.serviceappsxiaomun.model.request.paymentModel.PaymentRequest;
import co.id.xiaomun.serviceappsxiaomun.model.responseThirdParty.PaymentChannelResponse;
import co.id.xiaomun.serviceappsxiaomun.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${tripay.url-dev-merchant-payment}")
    private String channelPaymentMerchant;

    @Value("${tripay.url-dev}")
    private String urlDev;

    @Value("${tripay.authorization-header}")
    private String headerAuthDev;


    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public ResponseMap processPayment(PaymentRequest request) throws Exception {
        ResponseMap response = new ResponseMap();

        // 1. Simpan transaksi ke database
        Payment dataPayment = new Payment();
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

    @Override
    public ResponseMap getPaymentChannel(GetPaymentChannel request) throws Exception {
        ResponseMap response = new ResponseMap();

        if (request == null || request.getMessage() == null || request.getMessage().isEmpty()) {
            response.setStatus(false);
            response.setStatusCode("400");
            response.setResponseCode("01");
            response.setResponseMessage("Request cannot be null when getting payment channel");
            return response;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", headerAuthDev);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = urlDev;

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<?> apiResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    PaymentChannelResponse.class);

            Map<String, Object> result = new HashMap<>();
            result.put("Response from tripay (get payment channel) ", apiResponse.getBody());
            response.setStatus(true);
            response.setStatusCode("200");
            response.setResponseCode("00");
            response.setResponseMessage("Success");
            response.setResult(result);
        } catch (Exception e) {
            response.setStatus(false);
            response.setStatusCode("500");
            response.setResponseCode("99");
            response.setResponseMessage("Error calling external API: " + e.getMessage());
        }

        return response;
    }

    // ini untuk get response payment channel aktif in channel
    @Override
    public ResponseMap getPaymentChannelMerchant(GetPaymentChannelMerchant request) throws Exception {
        ResponseMap response = new ResponseMap();

        if (request == null || request.getMessage() == null || request.getMessage().isEmpty()) {
            response.setStatus(false);
            response.setStatusCode("400");
            response.setResponseCode("01");
            response.setResponseMessage("Request cannot be null when getting payment channel");
            return response;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", headerAuthDev);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        StringBuilder urlBuilder = new StringBuilder(channelPaymentMerchant);

        // Tambahkan ?channel=... jika ada
        if (request.getCode() != null && !request.getCode().isEmpty()) {
            String encodedChannel = URLEncoder.encode(request.getCode(), StandardCharsets.UTF_8);
            urlBuilder.append("?code=").append(encodedChannel);
        }
        System.out.println(urlBuilder);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<PaymentChannelResponse> apiResponse = restTemplate.exchange(
                    urlBuilder.toString(),
                    HttpMethod.GET,
                    entity,
                    PaymentChannelResponse.class);

            Map<String, Object> result = new HashMap<>();
            result.put("Response from tripay (get payment channel merchant)", apiResponse.getBody());

            response.setStatus(true);
            response.setStatusCode("200");
            response.setResponseCode("00");
            response.setResponseMessage("Success");
            response.setResult(result);
        } catch (Exception e) {
            response.setStatus(false);
            response.setStatusCode("500");
            response.setResponseCode("99");
            response.setResponseMessage("Error calling external API: " + e.getMessage());
        }

        return response;
    }

}
