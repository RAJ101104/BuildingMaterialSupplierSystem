package com.supplier.buildingmaterialsuppliersystem.repository;

import com.supplier.buildingmaterialsuppliersystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByCustomer_CustomerIdOrderByPaymentDateDesc(int customerId);
}