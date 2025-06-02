package co.id.xiaomun.serviceappsxiaomun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.id.xiaomun.serviceappsxiaomun.entity.payment.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    
}
