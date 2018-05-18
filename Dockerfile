FROM tomcat
MAINTAINER Paulo Ricardo de Souza <paulor1809@gmail.com>
COPY /lib/ojdbc7-12.1.0.2.jar /usr/local/tomcat/lib
COPY /lib/HikariCP-2.7.4.jar /usr/local/tomcat/lib
COPY /lib/slf4j-api-1.7.25.jar /usr/local/tomcat/lib
COPY /lib/server.xml /usr/local/tomcat/conf
ENV TZ=CST
COPY /api/build/libs/api.war /usr/local/tomcat/webapps/
RUN mkdir -p /usr/local/tomcat/webapps/eleanor
COPY /ui/dist/ /usr/local/tomcat/webapps/eleanor/

RUN chmod 775 -R /usr/local/tomcat/webapps
