# Subscription Insight Service

A production-style subscription management platform demonstrating event-driven microservices, AI-assisted decision support, and cloud-native deployment patterns for predicting and explaining customer churn risk.

## Architecture

![System Diagram](docs/system_diagram.png)

## Start up service & dependencies
* postgres
* kafka ( in KRaft mode )
* suscription-insight-service
```bash
docker compose up --build
```


## Check services are up

http://localhost:8080/actuator/health


## Swagger UI

http://localhost:8080/swagger-ui.html


