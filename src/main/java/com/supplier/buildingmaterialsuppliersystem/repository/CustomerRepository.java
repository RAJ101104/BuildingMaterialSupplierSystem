package com.supplier.buildingmaterialsuppliersystem.repository;

import com.supplier.buildingmaterialsuppliersystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}