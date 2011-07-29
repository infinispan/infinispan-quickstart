Infinispan CDI Quickstart
=========================

For further details about the CDI integration module look at https://docs.jboss.org/author/display/ISPN/CDI+Support

###Deploy the application with the JBoss AS Maven plugin

* Execute the following command `mvn clean package jboss-as:deploy`
* Open url http://localhost:8080/infinispan-cdi-quickstart/
* Enjoy :)

###Run Arquillian tests in JBoss AS 7

You can run the Arquillian tests with the following command `mvn clean test -Pas7-managed`

###See it in action

http://quickstart1291385363.prod.rhcloud.com/infinispan-cdi-quickstart/
