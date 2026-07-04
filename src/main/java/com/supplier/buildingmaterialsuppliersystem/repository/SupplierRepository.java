package com.supplier.buildingmaterialsuppliersystem.repository;

import com.supplier.buildingmaterialsuppliersystem.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}