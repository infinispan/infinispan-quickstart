Infinispan JBoss AS 7 Quickstart
================================

This quickstart demonstrates how create a simple, clustered, cache on *JBoss AS 7*.

The example can be deployed using Maven from the command line or from Eclipse using
JBoss Tools.

For more information, including how to set up Maven or JBoss Tools in Eclipse, 
refer to the [Getting Started Guide](https://docs.jboss.org/author/display/ISPN/Getting+Started+Guide+-+JBoss+AS+7).

###Deploy the application with the JBoss AS Maven plugin

* To deploy the Infinispan subsystem in JBoss AS 7 use `mvn jboss-as:add-resource`
* To deploy the application use the following command `mvn clean package jboss-as:deploy`
* Open
    * http://localhost:8080/infinispan-jboss-as7/seed to store datas in the cache
    * http://localhost:8080/infinispan-jboss-as7/log to see the cache events
* Enjoy :)
