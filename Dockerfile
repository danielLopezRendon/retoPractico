FROM amazoncorretto:21

VOLUME /tmp

ADD ./Cliente-0.0.1.jar Cliente-0.0.1.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=default", "/Cliente-0.0.1.jar"]


