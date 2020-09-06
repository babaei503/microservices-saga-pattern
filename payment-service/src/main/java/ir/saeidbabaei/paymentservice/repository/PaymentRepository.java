package ir.saeidbabaei.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ir.saeidbabaei.paymentservice.model.Payment;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
    Optional<Payment> findByOrderId(Long orderId);
    
}