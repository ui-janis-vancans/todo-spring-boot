FROM adoptopenjdk/openjdk8:alpine-jre

WORKDIR /root
COPY . /root/
CMD ["java", "-jar", "-Dspring.profiles.active=default", "build/libs/todo-spring-boot.jar"]
EXPOSE 8080/tcp
