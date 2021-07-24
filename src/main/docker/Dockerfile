FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} ipLocator-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "ipLocator-0.0.1-SNAPSHOT.jar"]