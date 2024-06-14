# Boutique Hotel Group BF

[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/bFlAvWr6)

## Build and Run for Development

### Backend

Start the database:

```bash
docker compose -f docker-compose.dev.yml up -d
```

Install dependencies:

```bash
cd BACKEND
mvn dependency:tree
```

Run the application:

```bash
mvn spring-boot:run
```

### Frontend

Install dependencies:

```bash
cd FRONTEND
npm install
```

Run the application using ionic:

```bash
npm run dev
```
