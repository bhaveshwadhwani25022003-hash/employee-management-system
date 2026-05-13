# 🏢 Employee Management System

A production-grade **RESTful API** built with **Java Spring Boot** featuring JWT authentication, MySQL persistence, and full CRUD operations for managing employees and departments.

---

## 🚀 Live Demo
> API Base URL: `https://your-app.railway.app` *(update after deployment)*

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.2 |
| Security | Spring Security + JWT |
| Database | MySQL 9.6 |
| ORM | Spring Data JPA / Hibernate |
| Build Tool | Maven |
| Deployment | Railway |

---

## ✨ Features

- 🔐 **JWT Authentication** — Register, login, and secure all endpoints with JSON Web Tokens
- 🔒 **BCrypt Password Encryption** — Passwords are never stored in plain text
- 👥 **Employee Management** — Full CRUD (Create, Read, Update, Delete)
- 🏗️ **Department Management** — Full CRUD with employee-department relationships
- 🔗 **Relational Data** — ManyToOne relationship between Employee and Department
- 🛡️ **Protected Routes** — All endpoints except `/auth/**` require a valid JWT token

---

## 📁 Project Structure

```
src/main/java/com/bhavesh/employeemanagement/
├── controller/
│   ├── AuthController.java
│   ├── DepartmentController.java
│   └── EmployeeController.java
├── model/
│   ├── User.java
│   ├── Department.java
│   └── Employee.java
├── repository/
│   ├── UserRepository.java
│   ├── DepartmentRepository.java
│   └── EmployeeRepository.java
├── security/
│   ├── JwtUtil.java
│   ├── JwtFilter.java
│   └── SecurityConfig.java
└── EmployeeManagementApplication.java
```

---

## ⚙️ Getting Started

### Prerequisites
- Java 17+
- MySQL 8+
- Maven

### 1. Clone the repository
```bash
git clone https://github.com/bhavesh38/employee-management-system.git
cd employee-management-system
```

### 2. Create MySQL database
```sql
CREATE DATABASE employeedb;
```

### 3. Configure application.properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employeedb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Run the application
```bash
./mvnw spring-boot:run
```

The API will start at `http://localhost:8080`

---

## 📡 API Endpoints

### 🔐 Auth
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/auth/register` | Register a new user | ❌ |
| POST | `/auth/login` | Login and get JWT token | ❌ |

### 🏗️ Departments
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/departments/add` | Add a new department | ✅ |
| GET | `/departments/all` | Get all departments | ✅ |
| GET | `/departments/{id}` | Get department by ID | ✅ |
| PUT | `/departments/update/{id}` | Update department | ✅ |
| DELETE | `/departments/delete/{id}` | Delete department | ✅ |

### 👥 Employees
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/employees/add` | Add a new employee | ✅ |
| GET | `/employees/all` | Get all employees | ✅ |
| GET | `/employees/{id}` | Get employee by ID | ✅ |
| GET | `/employees/department/{deptId}` | Get employees by department | ✅ |
| PUT | `/employees/update/{id}` | Update employee | ✅ |
| DELETE | `/employees/delete/{id}` | Delete employee | ✅ |

---

## 🔑 Authentication Flow

```
1. Register  →  POST /auth/register  →  { "username": "bhavesh", "password": "pass123" }
2. Login     →  POST /auth/login     →  returns JWT token
3. Use token →  Add header to all requests:
                Authorization: Bearer <your_token>
```

---

## 📬 Sample Requests

### Register
```json
POST /auth/register
{
  "username": "bhavesh",
  "password": "password123"
}
```

### Login
```json
POST /auth/login
{
  "username": "bhavesh",
  "password": "password123"
}

// Response:
"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiaGF2ZXNoIn0..."
```

### Add Department
```json
POST /departments/add
Authorization: Bearer <token>

{
  "name": "Engineering",
  "location": "Mumbai"
}
```

### Add Employee
```json
POST /employees/add
Authorization: Bearer <token>

{
  "name": "Bhavesh Wadhwani",
  "email": "bhavesh@company.com",
  "role": "Java Developer",
  "salary": 800000.0,
  "department": { "id": 1 }
}
```

---

## 🗄️ Database Schema

```
users
├── id (PK, AUTO_INCREMENT)
├── username (VARCHAR)
└── password (VARCHAR, BCrypt encrypted)

departments
├── id (PK, AUTO_INCREMENT)
├── name (VARCHAR)
└── location (VARCHAR)

employees
├── id (PK, AUTO_INCREMENT)
├── name (VARCHAR)
├── email (VARCHAR)
├── role (VARCHAR)
├── salary (DOUBLE)
└── department_id (FK → departments.id)
```

---

## 🔐 Security

- All endpoints except `/auth/register` and `/auth/login` are protected
- JWT tokens expire after **10 hours**
- Passwords are encrypted using **BCrypt**
- Stateless session management (no server-side sessions)

---

## 👨‍💻 Author

**Bhavesh Wadhwani**
- 📧 bhavesh.wadhwani25022003@gmail.com
- 💼 [LinkedIn](https://linkedin.com/in/bhaveshwadhwani)
- 🦊 [GitLab](https://gitlab.com/bhavesh38)
- 💻 [GitHub](https://github.com/bhavesh38)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).