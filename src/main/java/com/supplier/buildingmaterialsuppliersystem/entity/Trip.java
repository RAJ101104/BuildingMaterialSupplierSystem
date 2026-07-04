package com.supplier.buildingmaterialsuppliersystem.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String materialName;   // Sand, Gitti, Murum
    private double quantity;
    private String unit;           // brass, ton, trip
    private double ratePerUnit;
    private double totalAmount;
    private String vehicleNumber;
    private String driverName;
    private LocalDate tripDate;

    public Trip() {}

    public Trip(Customer customer, String materialName, double quantity, String unit,
                double ratePerUnit, String vehicleNumber, String driverName, LocalDate tripDate) {
        this.customer = customer;
        this.materialName = materialName;
        this.quantity = quantity;
        this.unit = unit;
        this.ratePerUnit = ratePerUnit;
        this.totalAmount = quantity * ratePerUnit;
        this.vehicleNumber = vehicleNumber;
        this.driverName = driverName;
        this.tripDate = tripDate;
    }

    public int getTripId() { return tripId; }
    public void setTripId(int tripId) { this.tripId = tripId; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
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
    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
    public LocalDate getTripDate() { return tripDate; }
    public void setTripDate(LocalDate tripDate) { this.tripDate = tripDate; }
}