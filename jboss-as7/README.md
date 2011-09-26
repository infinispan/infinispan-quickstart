Infinispan JBoss AS 7 Quickstart
================================

This quickstart demonstrates how create a simple, clustered, cache on *JBoss AS 7*.

The example can be deployed using Maven from the command line or from Eclipse using
JBoss Tools.

For more information, including how to set up Maven or JBoss Tools in Eclipse,
refer to the [Getting Started Guide](https://docs.jboss.org/author/display/ISPN/Getting+Started+Guide+-+JBoss+AS+7).

### Create a local cache using standalone mode

1) Edit `standalone/configuration/standalone.xml` and add a cache

    <cache-container name="jboss-as7-quickstart" default-cache="jboss-as7-quickstart-cache">
        <local-cache name="jboss-as7-quickstart-cache"/>
    </cache-container>

2) Start the server by runnig `bin/standalone.sh`

3) Package the app by running `mvn clean package`

4) Deploy the app using the management console at http://localhost:9990

5) Access the app at http://localhost:8080/infinispan-jboss-as7/home.jsf and put entries into the cache

### Create a distributed data-grid using domain mode

1) Edit `domain/configuration/domain.xml` and add a cache

    <cache-container name="jboss-as7-quickstart" default-cache="jboss-as7-quickstart-cache">
        <distributed-cache owners="1" mode="SYNC" name="jboss-as7-quickstart-cache" start="EAGER"/>
    </cache-container>

2) Ensure that you have an ha group starting, edit domain/configuration/host.xml, copy server-three to create a server-four, and change the port offset to `350`. Set both server-three and server-four to `auto-start="true"` and server-one and server-two to `auto-start="false"`.

3) Start the domain by running `bin/domain.sh`

4) Package the app by running `mvn clean package`

5) Deploy the app using the management console at http://localhost:9990

6) Access the app on both nodes (http://localhost:8330/infinispan-jboss-as7/home.jsf and http://localhost:8430/infinispan-jboss-as7/home.jsf) and put entries into the cache

### Use the JBoss AS Maven plugin

* To deploy the Infinispan subsystem use the following commands
    * `mvn -Pstandalone-local jboss-as:add-resource` for a local cache
    * `mvn -Pdomain-distributed jboss-as:add-resource` for a distributed cache
* To deploy the application use `mvn clean package jboss-as:deploy`
