package com.supplier.buildingmaterialsuppliersystem.controller;

import com.supplier.buildingmaterialsuppliersystem.entity.Trip;
import com.supplier.buildingmaterialsuppliersystem.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired private TripRepository tripRepository;

    @PostMapping
    public Trip addTrip(@RequestBody Trip trip) {
        trip.setTotalAmount(trip.getQuantity() * trip.getRatePerUnit());
        return tripRepository.save(trip);
    }

    @GetMapping
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable int id) {
        if (tripRepository.existsById(id)) {
            tripRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}