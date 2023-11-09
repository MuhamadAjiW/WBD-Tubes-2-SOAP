FROM tomee:8.0.15-jre8
WORKDIR /usr/local/tomee/webapps
COPY target/wbdsoap-1.0.war .
COPY .env_docker ./.env
EXPOSE 8080
RUN sed -i "s/8080/50000/g" /usr/local/tomee/conf/server.xml
CMD ["catalina.sh", "run"]