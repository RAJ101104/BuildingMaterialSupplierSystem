package com.supplier.buildingmaterialsuppliersystem.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseId;

    private String sourceName;      // Quarry/site name, e.g. "Wardha Quarry"
    private String materialName;    // Sand, Gitti, Murum
    private double quantity;
    private String unit;            // brass, ton, trip
    private double ratePerUnit;
    private double totalAmount;
    private double amountPaid;      // how much you've paid the source so far
    private String vehicleNumber;
    private String driverName;
    private LocalDate purchaseDate;

    public Purchase() {}

    public Purchase(String sourceName, String materialName, double quantity, String unit,
                    double ratePerUnit, double amountPaid, String vehicleNumber,
                    String driverName, LocalDate purchaseDate) {
        this.sourceName = sourceName;
        this.materialName = materialName;
        this.quantity = quantity;
        this.unit = unit;
        this.ratePerUnit = ratePerUnit;
        this.totalAmount = quantity * ratePerUnit;
        this.amountPaid = amountPaid;
        this.vehicleNumber = vehicleNumber;
        this.driverName = driverName;
        this.purchaseDate = purchaseDate;
    }

    public int getPurchaseId() { return purchaseId; }
    public void setPurchaseId(int purchaseId) { this.purchaseId = purchaseId; }
    public String getSourceName() { return sourceName; }
    public void setSourceName(String sourceName) { this.sourceName = sourceName; }
    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }
    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public double getRatePerUnit() { return ratePerUnit; }
    public void setRatePerUnit(double ratePerUnit) { this.ratePerUnit = ratePerUnit; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(double amountPaid) { this.amountPaid = amountPaid; }
    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
}