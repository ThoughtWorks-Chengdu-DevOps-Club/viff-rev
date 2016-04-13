FROM java:8
ENV JAR_FILE storage-0.0.1-SNAPSHOT.jar
ENV SPRINGBOOT_PROFILE docker
ENV JAVA_OPTS "-XX:+PrintGC"
ENV command "java -jar $JAR_FILE -Dspring.active.profile=$SPRINGBOOT_PROFILE $JAVA_OPTS"
COPY ./build/libs/$JAR_FILE /usr/src/
WORKDIR /usr/src/
CMD java -jar $JAR_FILE --spring.profiles.active=$SPRINGBOOT_PROFILE $JAVA_OPTS
EXPOSE 8008

