FROM openjdk:19
EXPOSE 8086
WORKDIR /opt
COPY etl ./etl
RUN chmod +x etl/tfe_master/tfe_master_run.sh
COPY target/reporting-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","./reporting-service-0.0.1-SNAPSHOT.jar"]