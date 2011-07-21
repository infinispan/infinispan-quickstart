Infinispan CDI Quickstart
=========================

###Deploy the application with the JBoss AS Maven plugin

* Package the application with `mvn clean package`
* Deploy the application with `mvn jboss-as:deploy`
* Enjoy :)

###Run Arquillian tests in JBoss AS 7

You can run the Arquillian tests with the following command `mvn clean test -Das7-managed`
