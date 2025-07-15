# المرحلة الأولى: بناء المشروع باستخدام Maven و JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

# المرحلة الثانية: تشغيل المشروع باستخدام JDK 17 فقط
FROM eclipse-temurin:17

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
