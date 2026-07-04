package com.supplier.buildingmaterialsuppliersystem.repository;

import com.supplier.buildingmaterialsuppliersystem.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findByCustomer_CustomerIdOrderByTripDateDesc(int customerId);
}