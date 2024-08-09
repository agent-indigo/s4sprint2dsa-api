FROM eclipse-temurin:21
COPY build/libs/*.jar ./s4sprint2dsa.api.jar
EXPOSE 8080
CMD ["java", "-jar", "s4sprint2dsa.api.jar"]