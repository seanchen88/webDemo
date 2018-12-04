FROM maven:3.5-jdk-8
COPY target/iot-demo-0.0.1-SNAPSHOT.jar /iot-demo.jar
EXPOSE 8080
ENTRYPOINT ["/bin/bash","-c","java -jar /iot-demo.jar --server.port=8080"]