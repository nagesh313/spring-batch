FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/spring-batch-0.0.1-SNAPSHOT.jar spring-batch-0.0.1-SNAPSHOT.jar
RUN sh -c 'touch /spring-batch-0.0.1-SNAPSHOT.jar'
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"
EXPOSE 8080 8787
ENTRYPOINT [ "sh", "-c", "mvn java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /spring-batch-0.0.1-SNAPSHOT.jar" ]