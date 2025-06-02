package co.id.xiaomun.serviceappsxiaomun.entity.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId; // ini dihasilin dari fe (gabungan tanggal + mount + total yang dibeli) 02062025100001

    private String customerName;

    private String paymentMethod;

    private String amount;

    private String status;

    private String transactionDate;

    private String callback;

    private String notes;

}
