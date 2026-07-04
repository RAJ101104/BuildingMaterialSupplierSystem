package com.supplier.buildingmaterialsuppliersystem.controller;

import com.supplier.buildingmaterialsuppliersystem.entity.Material;
import com.supplier.buildingmaterialsuppliersystem.entity.StockTransaction;
import com.supplier.buildingmaterialsuppliersystem.repository.MaterialRepository;
import com.supplier.buildingmaterialsuppliersystem.repository.StockTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private StockTransactionRepository stockTransactionRepository;

    @PostMapping
    public Material addMaterial(@RequestBody Material material) {
        return materialRepository.save(material);
    }

    @GetMapping
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable int id) {
        Optional<Material> material = materialRepository.findById(id);
        return material.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Material> updateMaterial(@PathVariable int id, @RequestBody Material updated) {
        return materialRepository.findById(id).map(material -> {
            material.setName(updated.getName());
            material.setCategory(updated.getCategory());
            material.setUnit(updated.getUnit());
            material.setPricePerUnit(updated.getPricePerUnit());
            material.setStockQuantity(updated.getStockQuantity());
            material.setSupplier(updated.getSupplier());
            return ResponseEntity.ok(materialRepository.save(material));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // STOCK IN - logs a transaction too
    @PutMapping("/{id}/stock-in")
    public ResponseEntity<?> stockIn(@PathVariable int id, @RequestParam double quantity,
                                     @RequestParam(required = false, defaultValue = "") String remarks) {
        Optional<Material> opt = materialRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Material material = opt.get();
        material.setStockQuantity(material.getStockQuantity() + quantity);
        materialRepository.save(material);

        StockTransaction txn = new StockTransaction(material, "IN", quantity, remarks);
        stockTransactionRepository.save(txn);

        return ResponseEntity.ok(material);
    }

    // STOCK OUT - logs a transaction too
    @PutMapping("/{id}/stock-out")
    public ResponseEntity<?> stockOut(@PathVariable int id, @RequestParam double quantity,
                                      @RequestParam(required = false, defaultValue = "") String remarks) {
        Optional<Material> opt = materialRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Material material = opt.get();
        if (material.getStockQuantity() < quantity) {
            return ResponseEntity.badRequest().body("Not enough stock available");
        }
        material.setStockQuantity(material.getStockQuantity() - quantity);
        materialRepository.save(material);

        StockTransaction txn = new StockTransaction(material, "OUT", quantity, remarks);
        stockTransactionRepository.save(txn);

        return ResponseEntity.ok(material);
    }

    // HISTORY - get all transactions for one material
    @GetMapping("/{id}/history")
    public List<StockTransaction> getStockHistory(@PathVariable int id) {
        return stockTransactionRepository.findByMaterial_MaterialIdOrderByTransactionDateDesc(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable int id) {
        if (!materialRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        List<StockTransaction> history = stockTransactionRepository
                .findByMaterial_MaterialIdOrderByTransactionDateDesc(id);
        stockTransactionRepository.deleteAll(history);

        materialRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}