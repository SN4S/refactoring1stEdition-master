# Video Store - Spring Boot Refactoring

The original Video Store project has been refactored from a procedural design to a layered Spring Boot application with proper separation of concerns.

## Architecture

The application follows a layered architecture:

```
example/
├── VideoStoreApplication.java    # Spring Boot entry point
├── controller/                   # REST layer
│   └── CustomerController.java
├── service/                      # Business logic
│   ├── StatementService.java
│   └── RentalService.java
└── model/                        # Entities
    ├── Customer.java
    ├── Movie.java
    └── Rental.java
```

## Refactoring Steps Applied

### 1. **Extract Method**
- Extracted rental calculation logic from `Customer.statement()` into `RentalService.calculateAmount()`
- Extracted frequent renter points logic into `RentalService.calculateFrequentRenterPoints()`

### 2. **Move Method**
- Moved business logic from `Customer` class to dedicated service classes
- Separated concerns: domain models vs. business logic

### 3. **Introduce Service Layer**
- Created `RentalService` for rental-specific calculations
- Created `StatementService` for statement generation logic
- Applied Dependency Injection via constructor injection

### 4. **Introduce Controller Layer**
- Created `CustomerController` as REST endpoint
- Exposed statement generation as `POST /customers/statement`

### 5. **Package Reorganization**
- Moved domain classes to `example.model` package
- Organized services in `example.service` package
- Organized controllers in `example.controller` package

### 6. **Spring Boot Integration**
- Added Spring Boot parent and dependencies
- Configured `@SpringBootApplication` entry point
- Applied `@Service` and `@RestController` annotations
- Enabled auto-configuration and component scanning

### 7. **Test Migration**
- Migrated from JUnit 4 to JUnit 5
- Refactored `CustomerTest` to `StatementServiceTest`
- Updated test assertions and annotations

### 8. **API Documentation**
- Added SpringDoc OpenAPI (Swagger UI)
- Enabled interactive API testing in browser

## Testing the API

### Option 1: Swagger UI (Browser)
1. Start the application
2. Open your browser and navigate to: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
3. Expand the `customer-controller` section
4. Click on `POST /customers/statement`
5. Click "Try it out"
6. Enter the JSON body (example below)
7. Click "Execute"

### Option 2: cURL (Command Line)
```bash
curl -X POST http://localhost:8080/customers/statement \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "rentals": [
      {
        "movie": {
          "title": "The Matrix",
          "priceCode": "REGULAR"
        },
        "daysRented": 3
      },
      {
        "movie": {
          "title": "Inception",
          "priceCode": "NEW_RELEASE"
        },
        "daysRented": 2
      },
      {
        "movie": {
          "title": "Toy Story",
          "priceCode": "CHILDRENS"
        },
        "daysRented": 4
      }
    ]
  }'
```

### Expected Response
```
Rental Record for John Doe
	The Matrix	3.5
	Inception	6.0
	Toy Story	3.0
Amount owed is 12.5
You earned 4 frequent renter points
```

## API Endpoints

### POST /customers/statement
Generates a rental statement for a customer.

**Request Body:**
```json
{
  "name": "string",
  "rentals": [
    {
      "movie": {
        "title": "string",
        "priceCode": "REGULAR" | "NEW_RELEASE" | "CHILDRENS"
      },
      "daysRented": number
    }
  ]
}
```
