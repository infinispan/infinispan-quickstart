Rapid Stock Market Quickstart
=============================

This quickstart demonstrates the behaviour of a compatibility mode. Updates of shares are
being stored in a cache via HotRod client and retrieved via HTTP client over REST.

The quickstart consists of two applications: a server-side application and client-side application.

Steps needed to build and run the applications:

1) Build a server module for Infinispan Server. In this step, a class common to both clients
and the server is packaged in a single jar file and placed in a directory structure similar to
a server module.

    mvn clean package -Pprepare-server-module

2) Install the server module into the server.

   * Copy the prepared module into the server:

        cp -r target/modules ${ISPN_SERVER_HOME}/

   * Add the new module as a dependency for `org.infinispan.server.endpoint module`. I.e. add
     `<module name="org.infinispan.quickstart.compatibility.common"/>` to `module.xml` in
     `org.infinispan.server.endpoint` module.

3) Build the application.

    mvn clean package

4) Configure Infinispan server to use a proper configuration file.

   * Copy an example configuration file for a compatibility mode to a correct location where Infinispan server
     can pick it up.

        cp ${ISPN_SERVER_HOME}/docs/examples/configs/standalone-compatibility-mode.xml ${ISPN_SERVER_HOME}/standalone/configuration

   * Disable REST security by removing `security-domain` and `auth-method` attribute definitions
     from `<rest-connector>` configuration element.

5) Start Infinispan server

    ${ISPN_SERVER_HOME}/bin/standalone.sh -c standalone-compatibility-mode.xml

6) Open another terminal and start the server-side application - Market Updater

    mvn exec:java

7) Open yet another terminal and start the client-side application

    mvn exec:java -Pclient

8) Follow the help for the client application and try it out!
