svn co --username=ftaylor92 https://svn-fmtmac.forge.cloudbees.com/REST-DB-JSP-Servlet-Jersey/

http://rest-db-jsp-servlet-jersey.fmtmac2.cloudbees.net/upload

http://rest-db-jsp-servlet-jersey.fmtmac2.cloudbees.net

http://rest-db-jsp-servlet-jersey.fmtmac2.cloudbees.net/hello
http://rest-db-jsp-servlet-jersey.fmtmac2.cloudbees.net/rest/hello - uses testCBDB URL
http://rest-db-jsp-servlet-jersey.fmtmac2.cloudbees.net/rest/db - uses jdbc URL
http://rest-db-jsp-servlet-jersey.fmtmac2.cloudbees.net/rest/sundial - Az/El values
http://localhost:8080/full-j2EE/rest/sundial
http://rest-db-jsp-servlet-jersey.fmtmac2.cloudbees.net/rest/httpcache?url=<encodedUrl>
http://localhost:8080/full-j2EE/HttpCache.jsp

# = Not valid in fmtmac2 account

/db.jsp - uses jdbc URL
#/cbdb.jsp - uses testCBDB URL and JSF

/upload-form.jsp - file upload to /var/lib/tomcat7/webapps/full-j2EE/tmp/, which is relative to tomcat and unk if supported by cloudBees

#bees app:bind -db fmt-test -a rest-db-jsp-servlet-jersey -as testCBDB

#To deploy WAR directly (or just check in code and wait (if build-automatically on check-in is set in cloudBees repository)
#mvn clean compile package
#bees app:deploy target/full-j2EE.war -a fmtmac/rest-db-jsp-servlet-jersey

in META-INF/context.xml -> (see name, url, username, password

for fmtmac2 cloudbees account:
http://rest-db-jsp-servlet-jersey.fmtmac2.cloudbees.net
mvn clean compile package
#fails: bees app:deploy target/full-j2EE.war - fmtmac2/rest-db-jsp-servlet-jersey
goto: cloudbees site, then hit Apps, rest-db-jsp-servlet-jersey, upload WAR file, select target/full-j2EE.war

for heroku:
git init
git add pom.xml README.txt src/
git status
git remote add origin https://github.com/ftaylor92/mathematics.git
git push -u origin master
git pull
heroku create
heroku apps:rename <app-name>
git push heroku master
mvn clean compile package
 
heroku config
vi system.properties
git add system.properties 
git commit -m "system.properties"
git push heroku master
heroku ps:scale web=1
heroku open

