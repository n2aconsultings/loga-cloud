FROM openjdk:19
COPY target/enterprise-service-0.0.1-SNAPSHOT.jar /opt
WORKDIR /opt
ENTRYPOINT ["java","-jar","./enterprise-service-0.0.1-SNAPSHOT.jar"]