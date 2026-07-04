package com.supplier.buildingmaterialsuppliersystem.controller;

import com.supplier.buildingmaterialsuppliersystem.entity.Material;
import com.supplier.buildingmaterialsuppliersystem.entity.Supplier;
import com.supplier.buildingmaterialsuppliersystem.repository.MaterialRepository;
import com.supplier.buildingmaterialsuppliersystem.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @PostMapping
    public Supplier addSupplier(@RequestBody Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable int id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable int id) {
        if (!supplierRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        boolean hasMaterials = materialRepository.findAll().stream()
                .anyMatch(m -> m.getSupplier() != null && m.getSupplier().getSupplierId() == id);

        if (hasMaterials) {
            return ResponseEntity.badRequest().body("This supplier has materials linked to them. Please delete or reassign those materials first.");
        }

        supplierRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}