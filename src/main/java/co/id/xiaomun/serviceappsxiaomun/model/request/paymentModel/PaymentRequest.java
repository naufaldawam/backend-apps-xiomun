package co.id.xiaomun.serviceappsxiaomun.model.request.paymentModel;

import lombok.Data;

@Data
public class PaymentRequest {

    private String orderId; // ini dihasilin dari fe (gabungan tanggal + mount + total yang dibeli) 02062025100001

    private String customerName;

    private String paymentMethod; // ini ambil get pembayaran yang dia pilih

    private String amount;

}
