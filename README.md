
# 🐾 Virtual Pet Web Application 

> This project was developed for Assignment S5.02 and focuses on integrating **Generative AI for frontend development**, alongside a functional **Java + Spring Boot backend**.

---

## 📌 Overview

The **Virtual Pet Web App** allows users to register, care for, and manage digital pets. A **Generative AI** tool was used to create the frontend, while a RESTful backend was developed using Spring Boot.

The main goal was to **experiment with AI-assisted development**, evaluate the code quality, make necessary adjustments, and reflect on the technical and conceptual learning process.

---

## 🤖 AI Tool Used: v0.dev

The [**v0.dev**](https://v0.dev) platform by OpenAI was selected to generate the **frontend (React + Tailwind CSS)**.

### ✔️ Why v0.dev?

- Visual and intuitive interface.
- Generates clean, semantic React components.
- Tailwind CSS styling by default.
- Supports quick UI iteration and easy API integration.

---

## 📡 Interactions with the AI

### Prompt 1
> "Create a frontend project consisting of three screens using the endpoints in the attached openapi.json: - Login (GET /auth/login) - View pets (GET /pets) - View pet (GET /pets/{id})
After logging in, send the authorization header with the JWT bearer that comes from the GET /login response to the other two screens. The backend will be deployed on localhost:8080"

✅ Result: A React app with a login form, pet list, and pet detail view. The AI-generated code was functional but required adjustments for API integration.

### Prompt 2
> "Add a register button to the login window that allows you to create a new player using the POST /auth/register endpoint. Add a button to the pet view window that allows you to create a new pet using the POST /pets endpoint. Add a button to the pet view window that allows you to edit the pet's name, type, and color using the PUT /pets/{id} endpoint. Add a button to the pet view window that allows you to delete a pet using the DELETE /pets/{id} endpoint."

✅ Result: A functional UI with forms for registration, pet creation, editing, and deletion. The AI-generated code was adapted to use `axios` for API calls.

### Prompt 3
>"Add another "Administrator" window that can be opened after logging in only if the authority is set to "ADMIN" in the JWT. This window will list all pets using the endpoints GET /admin/pets, edit a pet with PUT /admin/pets/{id}, and delete a pet with DELETE /admin/pets/{id}."

✅ Result: A separate admin interface with role-based access control.

### Adjustments Made:
- Replaced `fetch` with `axios` for better error handling.
- Added client-side validation.
- Integrated JWT authentication.
- Conditional rendering based on user roles (`USER`, `ADMIN`).

---

## 🛠️ Backend – Spring Boot API

### Key Features:
- Full CRUD for virtual pets.
- Pet status management (hunger, mood, energy).
- JWT-based authentication.
- Role-based authorization: `ROLE_USER` and `ROLE_ADMIN`.
- API documentation using Swagger.

### Technologies:
- ☕ Java 22
- 🌱 Spring Boot 
- 🛠️ Maven
- 🗃️ H2 / MySQL
- 🔐 Spring Security + JWT
- 📃 Swagger/OpenAPI
- 📂 SLF4J + Logback (logging)
- ⚡ Spring Cache (with `@Cacheable`)

---

## 🔗 Frontend ↔ Backend Integration

### API Endpoints Used:
- `POST /auth/register`
- `POST /auth/login`
- `GET /pets`
- `POST /pets`
- `PUT /pets/{id}`
- `DELETE /pets/{id}`

### Security:
- JWT is used for authentication.
- Role-based middleware protects frontend routes and backend endpoints.
- Authorization is enforced in both the frontend and backend.

### Challenges:
- CORS configuration for cross-origin requests.
- Proper handling of JWT in `Authorization` headers.
- Synchronizing API paths between dev and prod environments.

## 🧪 Testing

### Integration Tests:
- Registration and login flows with JWT.
- Full CRUD operations on pets.
- Role validation and access restrictions.

🧪 A dedicated database schema (`virtualpets_test`) was used for testing purposes.

---

## 🔐 Roles & Authorization

| Role         | Permissions                                       |
|--------------|---------------------------------------------------|
| `ROLE_USER`  | View, update, and delete **their own** pets       |
| `ROLE_ADMIN` | Full access to **all** pets                       |

> Authorization middleware ensures users can only access what they're permitted to.

---

## 📈 Logging & Caching

- **Logging**: SLF4J + Logback used to trace important events and errors.
- **Caching**: Spring's caching mechanism (`@Cacheable`) speeds up frequent read operations.

---

## 💬 Personal Reflection

> Key takeaways from this project:
- Learned how to **collaborate strategically with generative AI**.
- Improved prompt engineering skills for better AI output.
- Understood how to **critically evaluate and adapt AI-generated code**.
- Strengthened full-stack development skills: **frontend ↔ backend ↔ security ↔ testing**.

Working with AI doesn't mean giving up control — it's about **amplifying human decision-making**.

---

## 🗂️ Documentation & Resources

- [🌐 Swagger UI – REST API Docs](http://localhost:8080/swagger-ui/index.html)
- 📄 [Project Presentation PDF](./presentation/S5.02_VirtualPets_Presentation.pdf)
- 💻 [GitHub Repository](https://github.com/your-username/virtual-pet-app)

---

## 🚀 Technologies Used

- 🧠 v0.dev (AI UI Generator)
- ⚛️ React + Tailwind CSS
- ☕ Java + Spring Boot
- 🧪 JUnit + Mockito
- 🗃️ H2 / MySQL
- 🔐 JWT + Spring Security
- 🧩 SLF4J + Logback
- 📦 Swagger (OpenAPI)

---

## 🐳🚀🚀 Deploying with Docker

This project uses a **multi-stage Dockerfile** to efficiently build and run the Java Spring Boot application.

### 📦 Prerequisites

- Docker installed and running.
- Navigate to the project root directory (where `pom.xml` and `Dockerfile` are located).

---

### 🔧 Deployment Steps

1. **Build the Docker image**:
   ```bash
   docker build -t virtual-pet-app .

2. **Run the Docker container**:
   ```bash
   docker run -p 8080:8080 virtual-pet-app
   ```
3. **Verify it’s running**:
   
   Open: http://localhost:8080
   
   Swagger docs: http://localhost:8080/swagger-ui/index.html

---

## ✅ Final Thoughts

This project proved that **Generative AI can be a powerful co-developer**, but it’s not a replacement for human logic or domain expertise.

> "AI extends our capabilities, but good software still requires thoughtful engineering."

---

## 📧 Contact

Author: **Vanina Vega**  
Email: [vanivegah@hotmail.com](mailto:vanivegah@hotmail.com)  
GitHub: [github.com/VaninaVega/virtualPet](https://github.com/your-username/virtual-pet-app)


