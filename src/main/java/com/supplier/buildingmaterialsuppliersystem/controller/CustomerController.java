package com.supplier.buildingmaterialsuppliersystem.controller;

import com.supplier.buildingmaterialsuppliersystem.entity.Customer;
import com.supplier.buildingmaterialsuppliersystem.entity.Payment;
import com.supplier.buildingmaterialsuppliersystem.entity.Trip;
import com.supplier.buildingmaterialsuppliersystem.repository.CustomerRepository;
import com.supplier.buildingmaterialsuppliersystem.repository.PaymentRepository;
import com.supplier.buildingmaterialsuppliersystem.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired private CustomerRepository customerRepository;
    @Autowired private TripRepository tripRepository;
    @Autowired private PaymentRepository paymentRepository;

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        if (!customerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        // Delete all trips and payments linked to this customer first
        List<Trip> trips = tripRepository.findByCustomer_CustomerIdOrderByTripDateDesc(id);
        tripRepository.deleteAll(trips);

        List<Payment> payments = paymentRepository.findByCustomer_CustomerIdOrderByPaymentDateDesc(id);
        paymentRepository.deleteAll(payments);

        customerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // LEDGER - total billed, total paid, balance due for one customer
    @GetMapping("/{id}/ledger")
    public ResponseEntity<?> getLedger(@PathVariable int id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isEmpty()) return ResponseEntity.notFound().build();

        List<Trip> trips = tripRepository.findByCustomer_CustomerIdOrderByTripDateDesc(id);
        List<Payment> payments = paymentRepository.findByCustomer_CustomerIdOrderByPaymentDateDesc(id);

        double totalBilled = trips.stream().mapToDouble(Trip::getTotalAmount).sum();
        double totalPaid = payments.stream().mapToDouble(Payment::getAmount).sum();
        double balanceDue = totalBilled - totalPaid;

        Map<String, Object> ledger = new HashMap<>();
        ledger.put("customer", customerOpt.get());
        ledger.put("trips", trips);
        ledger.put("payments", payments);
        ledger.put("totalBilled", totalBilled);
        ledger.put("totalPaid", totalPaid);
        ledger.put("balanceDue", balanceDue);

        return ResponseEntity.ok(ledger);
    }
    // SUMMARY - all customers with balance due, highest due first
    @GetMapping("/dues-summary")
    public List<Map<String, Object>> getDuesSummary() {
        List<Customer> customers = customerRepository.findAll();
        List<Map<String, Object>> summary = new java.util.ArrayList<>();

        for (Customer c : customers) {
            List<Trip> trips = tripRepository.findByCustomer_CustomerIdOrderByTripDateDesc(c.getCustomerId());
            List<Payment> payments = paymentRepository.findByCustomer_CustomerIdOrderByPaymentDateDesc(c.getCustomerId());

            double totalBilled = trips.stream().mapToDouble(Trip::getTotalAmount).sum();
            double totalPaid = payments.stream().mapToDouble(Payment::getAmount).sum();
            double balanceDue = totalBilled - totalPaid;

            if (balanceDue > 0) {
                Map<String, Object> entry = new HashMap<>();
                entry.put("customerId", c.getCustomerId());
                entry.put("name", c.getName());
                entry.put("phone", c.getPhone());
                entry.put("address", c.getAddress());
                entry.put("totalBilled", totalBilled);
                entry.put("totalPaid", totalPaid);
                entry.put("balanceDue", balanceDue);
                summary.add(entry);
            }
        }

        summary.sort((a, b) -> Double.compare((double) b.get("balanceDue"), (double) a.get("balanceDue")));
        return summary;
    }
}
