# Building Material Shop Manager

A full-stack web application to manage a building material supply business — suppliers, stock inventory, customer ledgers (hisob book), and quarry purchases, all in one dashboard.

🔗 **Live Demo:** [buildingmaterialsuppliersystem-production.up.railway.app](https://buildingmaterialsuppliersystem-production.up.railway.app/index.html)

## Features

- **Supplier Management** — add, view, and delete material suppliers
- **Materials & Stock** — track inventory with stock-in / stock-out actions and low-stock alerts
- **Stock Transaction History** — full audit log of every stock movement
- **Hisob Book (Customer Ledger)**
  - Add customers
  - Log delivery trips (material, quantity, rate, vehicle, driver)
  - Record customer payments
  - Auto-calculated balance due per customer
- **Pending Dues Summary** — quick view of all customers who still owe money, sorted by highest due
- **Printable Bills** — generate a printable delivery receipt for any trip
- **Purchases** — track material bought from source quarries/sites, including amount paid vs. pending
- **Mobile-friendly** — installable to a phone's home screen as a lightweight web app
- **Cloud-hosted** — deployed on Railway, accessible from anywhere

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 21, Spring Boot 4, Spring Data JPA |
| Database | MySQL |
| Frontend | HTML, CSS, vanilla JavaScript |
| Build Tool | Maven |
| Hosting | Railway |

## Project Structure

```
src/main/java/com/supplier/buildingmaterialsuppliersystem/
 ├── entity/         # JPA entities (Supplier, Material, Customer, Trip, Payment, Purchase, StockTransaction)
 ├── repository/     # Spring Data JPA repositories
 └── controller/     # REST API controllers

src/main/resources/
 ├── application.properties
 └── static/index.html   # Frontend dashboard
```

## Getting Started (Local Setup)

### Prerequisites
- Java 21+
- Maven
- MySQL Server

### Steps

1. Clone the repository
   ```
   git clone https://github.com/RAJ101104/BuildingMaterialSupplierSystem.git
   ```

2. Create a MySQL database
   ```sql
   CREATE DATABASE supplier_db;
   ```

3. Configure `src/main/resources/application.properties`
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/supplier_db
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   server.port=8081
   ```

4. Run the application
   ```
   mvn spring-boot:run
   ```

5. Open the dashboard
   ```
   http://localhost:8081/index.html
   ```

## API Overview

| Endpoint | Description |
|---|---|
| `GET/POST /api/suppliers` | List / add suppliers |
| `GET/POST /api/materials` | List / add materials |
| `PUT /api/materials/{id}/stock-in` | Add stock |
| `PUT /api/materials/{id}/stock-out` | Remove stock |
| `GET/POST /api/customers` | List / add customers |
| `GET /api/customers/{id}/ledger` | Get a customer's full ledger |
| `GET /api/customers/dues-summary` | List customers with pending dues |
| `GET/POST /api/trips` | List / log delivery trips |
| `GET/POST /api/payments` | List / record payments |
| `GET/POST /api/purchases` | List / log quarry purchases |

## Deployment

This project is deployed on [Railway](https://railway.app), with automatic redeployment on every push to the `main` branch. The MySQL database is also hosted on Railway.

## License

This project is for personal/business use.
