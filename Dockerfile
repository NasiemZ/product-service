FROM maven:3.8-openjdk-17
WORKDIR /ProduktMicroService
COPY . .
RUN mvn clean install --no-transfer-progress
CMD mvn spring-boot:run