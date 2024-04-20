FROM maven:3.8.1-openjdk-11

COPY . /app
WORKDIR /app

RUN mvn clean package

CMD ["java", "-jar", "target/testProject-1.0-SNAPSHOT.jar"]