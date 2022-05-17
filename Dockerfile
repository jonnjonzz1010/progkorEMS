FROM openjdk:17-jdk-alpine3.14

COPY "./target/employeeCatalog.jar" "/application/employeeCatalog.jar"

CMD ["java", "-jar", "/application/employeeCatalog.jar"]
