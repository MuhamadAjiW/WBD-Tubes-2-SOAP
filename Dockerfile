FROM tomcat:9-jdk8-corretto
COPY target/wbdsoap-1.0.war /usr/local/tomcat/webapps/
EXPOSE 8080