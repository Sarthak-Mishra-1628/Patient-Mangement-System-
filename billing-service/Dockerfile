FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# had to Install required system packages to make it run
#RUN apt-get update && apt-get install -y protobuf-compiler

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN #mvn clean package
RUN mvn clean package -Dmaven.test.skip

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder ./app/target/billing-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4001
EXPOSE 9001

ENTRYPOINT ["java" , "-jar" , "app.jar"]