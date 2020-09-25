FROM adoptopenjdk/openjdk8:alpine-jre

ARG JAVA_OPTIONS
ENV JAVA_TOOL_OPTIONS=${JAVA_OPTIONS}
WORKDIR /root
COPY build/libs/todo-spring-boot.jar /root/todo-app.jar
COPY src/main/webapp /root/src/main/webapp
CMD ["java", "-jar", "todo-app.jar"]
EXPOSE 8080/tcp
