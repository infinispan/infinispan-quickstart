Infinispan CDI Quickstart
=========================

For further details about the CDI integration module look at https://docs.jboss.org/author/display/ISPN/CDI+Support

###Deploy the application with the JBoss AS Maven plugin

* Package the application with `mvn clean package`
* Deploy the application with `mvn jboss-as:deploy`
* Open url http://localhost:8080/infinispan-cdi-quickstart/
* Enjoy :)

###Run Arquillian tests in JBoss AS 7

You can run the Arquillian tests with the following command `mvn clean test -Das7-managed`
