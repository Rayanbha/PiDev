FROM openjdk:8
EXPOSE 8080
ADD target/Pidev3-1.0-SNAPSHOT.jar Pidev3-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Pidev3-1.0-SNAPSHOT.jar"]
