FROM openjdk:8
ADD target/comment-service-0.0.1-SNAPSHOT.jar  comment-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","comment-service-0.0.1-SNAPSHOT.jar"]