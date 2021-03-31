FROM tomcat:9.0.44

RUN mkdir -p /usr/local/stampli/
ADD ./target/stampli-*.war /usr/local/stampli/stampli.war

# I don;t want to add more configuration yet
#RUN echo '<Host name="localhost"  appBase="webapps" \
#          unpackWARs="false" autoDeploy="false" deployOnStartup="false"> \
#          <Context path="/mydemo/version1" docBase="sampli.war"/> \
#    </Host>' > /usr/local/tomcat/conf/server.xml

RUN echo 'export JPDA_ADDRESS=*:1995' > /usr/local/tomcat/bin/setenv.sh

RUN mkdir -p /usr/local/tomcat/conf/Catalina/localhost
RUN echo '<Context                                       \
           docBase="/usr/local/stampli/stampli.war"      \
           path=""                                       \
           reloadable="false"                            \
           />' > /usr/local/tomcat/conf/Catalina/localhost/ROOT.xml


HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/stampli/test/string

CMD ["catalina.sh", "jpda",  "run"]

EXPOSE 8080
