Infinispan JBoss AS 7 Quickstart
================================

This quickstart demonstrates how create a simple, clustered, cache on *JBoss AS 7.1*.

The example can be deployed using Maven from the command line or from Eclipse using
JBoss Tools.

For more information, including how to set up Maven or JBoss Tools in Eclipse,
refer to the [Getting Started Guide](https://docs.jboss.org/author/display/ISPN/Getting+Started+Guide+-+JBoss+AS+7).

### Create a local cache using standalone mode

1) Edit `standalone/configuration/standalone.xml` and add a cache

    <cache-container name="jboss-as7-quickstart" default-cache="jboss-as7-quickstart-cache">
        <local-cache name="jboss-as7-quickstart-cache"/>
    </cache-container>

2) Start the server by running `bin/standalone.sh`

3) Package the app by running `mvn clean package`

4) Deploy the app using the management console at http://localhost:9990

5) Access the app at http://localhost:8080/infinispan-jboss-as7/home.jsf and put entries into the cache

### Create a distributed data-grid using domain mode

1) Edit `domain/configuration/domain.xml` and add a this cache container configuration to the `full-ha` profile:

    <cache-container name="jboss-as7-quickstart" default-cache="jboss-as7-quickstart-cache">
        <transport lock-timeout="60000"/>
        <distributed-cache owners="1" mode="SYNC" name="jboss-as7-quickstart-cache" start="EAGER"/>
    </cache-container>

2) Ensure that you have an ha group starting, edit domain/configuration/host.xml, copy server-three to create a server-four, and change the port offset to `350`. Set both server-three and server-four to `auto-start="true"` and server-one and server-two to `auto-start="false"`.

3) Change 'cluster-password' value in "urn:jboss:domain:messaging:1.1" subsystem to something else. For example:

    <cluster-password>new-password</cluster-password>

3) Start the domain by running `bin/domain.sh`

4) Package the app by running `mvn clean package`

5) Deploy the app using the management console at http://localhost:9990

6) Access the app on both nodes (http://localhost:8330/infinispan-jboss-as7/home.jsf and http://localhost:8430/infinispan-jboss-as7/home.jsf) and put entries into the cache

### Use the JBoss AS Maven plugin

* To deploy the Infinispan subsystem use the following commands
    * `mvn -Pstandalone-local jboss-as:add-resource` for a local cache
    * `mvn -Pdomain-distributed jboss-as:add-resource` for a distributed cache
* To deploy the application use `mvn clean package jboss-as:deploy`

### Quick testing data-grid in domain mode

This quick start ships a .cli script in the root folder that automatically applies the AS7 container changes required to get the domain part of the quick start running. To run this script:

* Start the domain in admin-only mode: `${JBOSS_HOME}/bin/domain.sh --admin-only`
* From the quickstart root folder, execute: `${JBOSS_HOME}/bin/jboss-cli.sh --file=datagrid-domain.cli`
* Restart the domain
