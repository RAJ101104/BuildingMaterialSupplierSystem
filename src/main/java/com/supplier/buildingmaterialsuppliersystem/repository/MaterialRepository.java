package com.supplier.buildingmaterialsuppliersystem.repository;

import com.supplier.buildingmaterialsuppliersystem.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
}