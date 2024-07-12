FROM openjdk:17-jdk

EXPOSE 8080
ARG JAR_FILE=target/transactions-pismo-challenge.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]