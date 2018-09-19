FROM openjdk:8
#VOLUME /tmp
ADD /target//weq-ads-tracker/target/weq-ads-trackerv3-0.0.1-SNAPSHOT.jar.jar  weq-ads-tracking.jar
ENTRYPOINT ["java","-server","-Xms1g","-Xmx1g","-XX:+UseParallelGC","-jar","/weq-ads-tracking.jar"]
