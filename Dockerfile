FROM java:8

ADD /target/way-v1.jar way-v1.jar
ENTRYPOINT ["java","-jar", "way-v1.jar"]