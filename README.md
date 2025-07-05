# 🏥 Patient Management System – Cloud-Native Microservices

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.4.7-green)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue)
![Kafka](https://img.shields.io/badge/Kafka-Protobuf-orange)

---

## 📝 Description

A production-grade **microservices-based healthcare backend** built using Spring Boot, gRPC, Kafka, Docker, PostgreSQL, and AWS CDK + LocalStack. Features secure authentication, scalable architecture, and cloud-native deployment support.

---

## ⚙️ Tech Stack

- Java 17 + Spring Boot 3.4.7
- Microservices + gRPC
- Kafka + Protobuf
- PostgreSQL + JPA
- Docker + AWS CDK
- Spring Security + JWT
- API Gateway with custom token validation
- LocalStack for infra simulation

---

## 📦 Microservices

| Service Name       | Port  | Description                                     |
|--------------------|-------|-------------------------------------------------|
| `auth-service`     | 4005  | Issues and validates JWT tokens                 |
| `patient-service`  | 4000  | Manages patient CRUD + gRPC + Kafka producer    |
| `billing-service`  | 4001  | gRPC server for billing accounts                |
| `analytics-service`| 4002  | Kafka consumer for patient events               |
| `api-gateway`      | 4004  | Routes requests and validates JWT tokens        |

---

## 🚀 Features

- 🔗 gRPC-based communication (Patient → Billing)
- ⚡ Kafka-based streaming (Patient → Analytics) with Protobuf
- 🔐 JWT authentication validated at API Gateway
- 🐳 Dockerized services
- ☁ Local cloud infra using AWS CDK + LocalStack (ECS, RDS, MSK)
- 💾 PostgreSQL database integration with Spring JPA

---

## 🔐 Authentication

- Token issued via `/auth/login`
- Protected endpoints require:  
  `Authorization: Bearer <token>`

---


