FROM adoptopenjdk/openjdk8:alpine-jre

WORKDIR /root
COPY . /root/
CMD ["java", "-jar", "build/libs/todo-spring-boot.jar"]
EXPOSE 8080/tcp
