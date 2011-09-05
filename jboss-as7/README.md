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


### Create a distributed data-grid using domain mode

1) Edit domain/configuration/domain.xml and add a cache
                <cache-container name="jboss-as7-quickstart" default-cache="jboss-as7-quickstart-cache">
                    <distributed-cache owners="1" mode="SYNC" name="jboss-as7-quickstart-cache" start="EAGER"/>
                </cache-container>

2) Ensure that you have an ha group starting, edit domain/configuration/host.xml, copy server-three to create a server-four, and change the port offset to 350. Set both server-three and server-four to auto-start="true" and server-one and server-two to auto-start="false".

3) Start the domain by running bin/domain.xml

4) package the app by running mvn clean package

5) deploy the app using the management console at localhost:9990

6) access the app on both nodes (http://localhost:8330/infinispan-jboss-as7/home.jsf and http://localhost:8430/infinispan-jboss-as7/home.jsf) and put entries into the cache
