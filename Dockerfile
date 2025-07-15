# مرحلة البناء
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src ./src

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# مرحلة التشغيل
FROM eclipse-temurin:17

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
