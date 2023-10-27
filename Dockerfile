FROM tomcat:latest
COPY target/tubes-2-wbd-soap-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/
EXPOSE 8080