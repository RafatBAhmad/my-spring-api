# استخدم صورة رسمية لـ Maven مع JDK 17 أو 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

# انسخ كل ملفات المشروع داخل الكونتينر
COPY . /app
WORKDIR /app

# أبني المشروع باستخدام Maven
RUN mvn clean package -DskipTests

# ---------------------------------------
# المرحلة الثانية: تشغيل المشروع
FROM eclipse-temurin:21

WORKDIR /app

# انسخ الـ JAR المبني من المرحلة السابقة
COPY --from=build /app/target/*.jar app.jar

# شغّل التطبيق
ENTRYPOINT ["java", "-jar", "app.jar"]
