# Employee Performance Management System (Spring Boot)

A RESTful service to fetch and filter employee performance data.

## Tech Stack
- Java 8
- Spring Boot
- MySQL
- JPA/Hibernate
- Lombok

## How to Run
1. Set up MySQL database `employee_db`
2. Update `application.properties` with your DB credentials
3. Run `EmployeeManagementApplication.java`

## Sample Endpoints

- Get filtered employees  
  `GET /api/employees?reviewDate=2024-06-01&departments=IT&projects=Migration`

- Get employee details by ID  
  `GET /api/employees/1`
