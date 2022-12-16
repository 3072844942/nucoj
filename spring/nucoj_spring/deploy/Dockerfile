FROM java:8

VOLUME /tmp
ADD nucoj_spring-0.0.1.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]