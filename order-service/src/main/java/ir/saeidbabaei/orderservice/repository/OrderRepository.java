package ir.saeidbabaei.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ir.saeidbabaei.orderservice.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
}