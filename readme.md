# Real-Time Chat Application

A real-time chat application built using **Java Spring Boot**, **Thymeleaf**, **Bootstrap**, **WebSocket**, **STOMP.js**, and **SockJS**.\
This project enables instant messaging between multiple users with a responsive UI and WebSocket-based communication.

---

## 🚀 Features

- Real-time messaging using WebSocket with STOMP and SockJS fallback.
- Responsive frontend with **Bootstrap** and **Thymeleaf** templates.
- User-friendly UI with auto-hiding labels and aligned message layout (text + time in the same line).
- Lightweight and extensible backend powered by **Spring Boot**.
- Supports broadcasting messages to all connected users.

---

## 🌍 Tech Stack

- **Backend:** Java, Spring Boot (WebSocket, STOMP)
- **Frontend:** Thymeleaf, Bootstrap, JavaScript
- **WebSocket Client:** STOMP.js, SockJS
- **Build Tool:** Maven/Gradle
- **Server:** Embedded Tomcat (Spring Boot default)

---

## 📂 Project Structure

```
src/
 ├── main/
 │   ├── java/com/example/chat
 │   │   ├── controller/      # WebSocket and page controllers
 │   │   ├── model/           # DTOs and message objects
 │   │   └── config/          # WebSocket configuration
 │   └── resources/
 │       ├── static/          # CSS, JS
 │       ├── templates/       # Thymeleaf HTML templates
 │       └── application.yml  # Config file
```

---

## ⚡ How It Works

1. **Client connects** to the WebSocket endpoint (`/ws`) using **SockJS** and **STOMP.js**.
2. When a user sends a message:
   - It is sent to the broker (`/app/chat.sendMessage`).
   - The broker publishes the message to all subscribers (`/topic/public`).
3. **Frontend** receives and displays the message with timestamp.

---

## ▶️ Getting Started

### Prerequisites

- Java 17+ (recommended)
- Maven or Gradle
- Any IDE (IntelliJ, Eclipse, VS Code with Java support)

### Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/Bhavya251/zerohour-springboot-websocket-thymeleaf.git
   cd zerohour
   ```

2. Build and run:

   ```bash
   mvn spring-boot:run
   ```

3. Open the app in your browser:

   ```
   http://localhost:8080/chat
   ```

---

## 💡 Future Improvements

- Add private chat support.
- Store messages in a database (PostgreSQL/MySQL).
- Add authentication & user management.
- Enhance UI with message delivery/read indicators.

---

## 📜 License

This project is open-source and available under the [MIT License](LICENSE).

