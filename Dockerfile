FROM openjdk:8
ADD target/artific-on-crawler.jar docker-artific-crawler.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "docker-artific-crawler.jar"]