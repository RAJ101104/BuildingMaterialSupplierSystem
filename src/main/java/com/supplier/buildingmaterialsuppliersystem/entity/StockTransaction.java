package com.supplier.buildingmaterialsuppliersystem.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_transactions")
public class StockTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    private String type;          // "IN" or "OUT"
    private double quantity;
    private String remarks;       // e.g., "Delivery from ABC Suppliers" or "Sold to customer"
    private LocalDateTime transactionDate;

    public StockTransaction() {}

    public StockTransaction(Material material, String type, double quantity, String remarks) {
        this.material = material;
        this.type = type;
        this.quantity = quantity;
        this.remarks = remarks;
        this.transactionDate = LocalDateTime.now();
    }

    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }
    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
}