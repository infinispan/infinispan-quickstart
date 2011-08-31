Infinispan CDI Quickstart
=========================

This quickstart demonstrates the Infinispan *CDI Support*.

The example can be deployed using Maven from the command line or from Eclipse using
JBoss Tools.

For more information refer to the CDI Support [documentation](https://docs.jboss.org/author/display/ISPN/CDI+Support).

###Deploy the application with the JBoss AS Maven plugin

* Execute the following command `mvn clean package jboss-as:deploy`
* Open http://localhost:8080/infinispan-cdi/
* Enjoy :)

###Run Arquillian tests in JBoss AS 7

You can run the Arquillian tests with the following command `mvn clean test -Pas7-managed`
