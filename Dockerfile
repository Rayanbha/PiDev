FROM openjdk:8
EXPOSE 8080
ADD target/stage.jar stage.jar
ENTRYPOINT ["java","-jar","/stage.jar"]
