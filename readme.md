# Chat Application - Microservices Architecture (Backend)

This project is a **Chat Application** built using **Java 21**, **Spring Boot**, **Spring Cloud**, **Apache Kafka**, and **PostgreSQL**.  
It follows a **microservices architecture** with each service responsible for a specific concern.

---

## 📌 Microservices Overview

### 1. **User Service (`user-service`)**
- Handles user registration and authentication.
- Exposes REST APIs for user details.
- Provides integration with other services via **OpenFeign**.

### 2. **Chat Service (`chat-service`)**
- Handles sending and retrieving chat messages.
- Stores chat messages in **PostgreSQL**.
- Publishes events to **Kafka** for audit logging.

### 3. **Kafka Service (`kafka-service`)**
- Consumes chat messages from **Kafka**.
- Stores all chat logs in `audit_logs` table for monitoring and auditing.
- Provides API to fetch logs (future use for frontend).

---

## 🛠️ Tech Stack
- **Java 21**
- **Spring Boot 3.2.x**
- **Spring Cloud 2023.x**
- **Spring Data JPA**
- **PostgreSQL**
- **Apache Kafka**
- **Maven**
- **OpenFeign** (for inter-service communication)
- **Docker** (optional, for DB & Kafka)

---

## 📂 Project Structure
```
chat-application/
│── pom.xml (Parent POM)
│── user-service/
│── chat-service/
│── kafka-service/
```

---

## 🚀 Getting Started

### 1️⃣ Clone the Repository
```bash
git clone <repo-url>
cd chat-application
```

### 2️⃣ Start Dependencies
You need PostgreSQL and Kafka running.

#### Option A: Using Docker
```bash
docker run --name postgres-chat -e POSTGRES_USER=chatuser -e POSTGRES_PASSWORD=chatpass -e POSTGRES_DB=chatdb -p 5432:5432 -d postgres:15

docker run --name zookeeper -p 2181:2181 -d wurstmeister/zookeeper:3.4.6
docker run --name kafka -p 9092:9092 --link zookeeper -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -d wurstmeister/kafka:2.12-2.2.1
```

#### Option B: Local Install
- Install PostgreSQL and run it on `localhost:5432`
- Install Kafka and Zookeeper manually

---

### 3️⃣ Build and Run Microservices

Each microservice can be started separately.

#### User Service
```bash
cd user-service
mvn spring-boot:run
```

#### Chat Service
```bash
cd chat-service
mvn spring-boot:run
```

#### Kafka Service
```bash
cd kafka-service
mvn spring-boot:run
```

---

## 🔑 Default Configuration
Update `application.properties` for each service as needed.

### Example (chat-service)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/chatdb
spring.datasource.username=chatuser
spring.datasource.password=chatpass
spring.jpa.hibernate.ddl-auto=update

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=chat-group
```

---

## ✅ Testing with Postman
1. Start **all three services** (`user-service`, `chat-service`, `kafka-service`).
2. Register a new user via `user-service`.
3. Send a chat message via `chat-service`.
4. Verify that it is consumed and stored by `kafka-service` in `audit_logs`.

---

## 📌 Next Steps
- Build React frontend with Dark Neomorphism UI
- Integrate group chat functionality
- New Features like file sharing and EMOJI. 
