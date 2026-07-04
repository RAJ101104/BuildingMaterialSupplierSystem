package com.supplier.buildingmaterialsuppliersystem.controller;

import com.supplier.buildingmaterialsuppliersystem.entity.Purchase;
import com.supplier.buildingmaterialsuppliersystem.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @PostMapping
    public Purchase addPurchase(@RequestBody Purchase purchase) {
        purchase.setTotalAmount(purchase.getQuantity() * purchase.getRatePerUnit());
        return purchaseRepository.save(purchase);
    }

    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable int id) {
        if (purchaseRepository.existsById(id)) {
            purchaseRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}