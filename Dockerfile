FROM openjdk:8
EXPOSE 8080
ADD target/Stage.jar Stage.jar
ENTRYPOINT ["java","-jar","/Stage.jar"]
