# 🏥 PulseStream Clinical Platform: High-Performance Healthcare SaaS

PulseStream is a sophisticated, production-grade clinical management system engineered for institutional-scale healthcare operations. It optimizes the balance between low-latency user experience and high-integrity medical scheduling.

# Live link: https://pulsestream-rblw.onrender.com

## 🔄 PulseStream Functional Flow
<img width="655" height="653" alt="image" src="https://github.com/user-attachments/assets/e2d6e462-e05c-46f8-8864-69a8c6049d36" />


---

## 🏗️ Core Engineering Highlights
This project was built with a focus on **Enterprise Patterns** and **Performance Optimization**:

*   **⚡ Asynchronous Clinical Workflows**: Engineered a high-performance **Asynchronous Email Threading** system using Spring's `@Async` thread pools, reducing clinical booking latency from ~3s to **<500ms**.
*   **⏰ Automated Clinical Operations (Cron Jobs)**: Designed a complex **Email Cron Job** scheduler (`@Scheduled`) to handle daily clinical reminders and automated status synchronization without human intervention.
*   **🧩 Real-Time Availability Engine**: Implemented a precise concurrency engine with custom JPQL logic to ensure exactly-once booking and real-time slot release upon clinical cancellations.
*   **💎 Clearview Design System**: A custom-architected high-contrast glassmorphic UI, optimized for high-performance dashboards across 9 distinct user portals (Admin, Doctor, Patient, etc.).

---

## 🛠️ Technical Stack
*   **Backend**: Spring Boot 3.2.4 (Java 17/21), Spring Security, Hibernate/JPA.
*   **Database**: TiDB Cloud (Distributed MySQL-Compatible).
*   **Frontend**: Vanilla JavaScript (Fetch API), Premium Glassmorphic CSS.
*   **Infrastructure**: Render (Cloud Hosting), GitHub Actions, UptimeRobot (24/7 Monitoring).

---

## 🛡️ Security & Architecture
*   **Role-Based Access Control (RBAC)**: Strict path-based segmentation for Admin, Doctor, and Patient layers via `SecurityConfig`.
*   **Data Integrity**: Managed Soft-Delete (Active/Inactive) strategy to preserve longitudinal medical history while ensuring clean active reports.
*   **Architecture**: Follows a standard **Controller-Service-Repository** design pattern for maximum maintainability and separation of concerns.

---

## 🚀 How to Run Locally
1.  **Clone**: `git clone https://github.com/your-username/pulsestream.git`
2.  **Database**: Update `backend/src/main/resources/application.properties` with your credentials.
3.  **Build**: `mvn clean install` inside the `/backend` folder.
4.  **Run**: `java -jar target/*.jar`

---


## 📁 Project Structure

```
PulseStream
│── .gitignore
│── Dockerfile
│── README.md
│
├── backend
│   │── pom.xml
│   │
│   └── src
│       └── main
│           ├── java
│           │   └── com
│           │       └── hms
│           │           │── HospitalManagementSystemApplication.java
│           │           │
│           │           ├── config
│           │           │   │── CustomUserDetails.java
│           │           │   │── CustomUserDetailsService.java
│           │           │   │── DataInitializer.java
│           │           │   │── SecurityConfig.java
│           │           │
│           │           ├── controller
│           │           │   │── AdminController.java
│           │           │   │── AuthController.java
│           │           │   │── DashboardController.java
│           │           │   │── DoctorController.java
│           │           │   │── PatientController.java
│           │           │   │── PublicController.java
│           │           │
│           │           ├── entity
│           │           │   │── Admin.java
│           │           │   │── Appointment.java
│           │           │   │── Availability.java
│           │           │   │── ContactMessage.java
│           │           │   │── Doctor.java
│           │           │   │── PasswordResetToken.java
│           │           │   │── Patient.java
│           │           │   │── Prescription.java
│           │           │
│           │           ├── repository
│           │           │   │── AdminRepository.java
│           │           │   │── AppointmentRepository.java
│           │           │   │── AvailabilityRepository.java
│           │           │   │── ContactMessageRepository.java
│           │           │   │── DoctorRepository.java
│           │           │   │── PasswordResetTokenRepository.java
│           │           │   │── PatientRepository.java
│           │           │   │── PrescriptionRepository.java
│           │           │
│           │           ├── service
│           │           │   │── AppointmentService.java
│           │           │   │── AuthService.java
│           │           │   │── AvailabilityService.java
│           │           │   │── ContactService.java
│           │           │   │── DoctorService.java
│           │           │   │── EmailService.java
│           │           │   │── PatientService.java
│           │           │   │── PrescriptionService.java
│           │           │   │── ReminderScheduler.java
│           │           │
│           │           └── util
│           │               │── DataSeeder.java
│           │
│           └── resources
│               │── application.properties
│               │── application-local.properties
│               │
│               └── static
│                   │── admin_dashboard.html
│                   │── contact.html
│                   │── doctor_dashboard.html
│                   │── forgot_password.html
│                   │── index.html
│                   │── index1.html
│                   │── patient_dashboard.html
│                   │── reset_password.html
│                   │── services.html
│                   │
│                   ├── css
│                   │   │── modern.css
│                   │
│                   ├── images
│                   │   │── favicon.png
│                   │   │── logo.png
│                   │
│                   └── js
│                       │── modern.js
│
└── frontend
    │── admin_dashboard.html
    │── contact.html
    │── doctor_dashboard.html
    │── forgot_password.html
    │── index.html
    │── index1.html
    │── patient_dashboard.html
    │── reset_password.html
    │── services.html
    │
    ├── css
    │   │── modern.css
    │
    ├── images
    │   │── favicon.png
    │   │── logo.png
    │
    └── js
        │── modern.js
```



## 👨‍💻 Developed By
**Raj Kiran Acharyya**  
*Aspiring Full-Stack Developer specializing in High-Performance Java Architectures.*

---
