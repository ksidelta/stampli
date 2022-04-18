FROM maven:3.8.5-openjdk-17

ADD ./ /app

# HEALTHCHECK --interval=30s --timeout=3s \
# CMD curl -f http://localhost:8080/stampli/test/string

WORKDIR /app
RUN ["mvn", "compile"]
CMD ["mvn", "spring-boot:run"]

EXPOSE 8080
