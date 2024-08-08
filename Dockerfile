FROM eclipse-temurin:21
COPY target/*.jar ./s4sprint2dsa.jar
EXPOSE 8080
CMD ["java", "-jar", "s4sprint2dsa.jar"]