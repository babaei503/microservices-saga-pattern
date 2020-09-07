package ir.saeidbabaei.stockservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ir.saeidbabaei.stockservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}