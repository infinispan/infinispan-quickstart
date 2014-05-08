Clustered Cache Quickstart
==========================

This quickstart demonstrates *embedded cache* running on *two node* in 
*Java SE*.

The example can be deployed using Maven from the command line or from Eclipse using
JBoss Tools.

For more information, including how to set up Maven or JBoss Tools in Eclipse, 
refer to the [Getting Started Guide](https://docs.jboss.org/author/display/ISPN/Getting+Started+Guide+-+Clustered+Cache+in+Java+SE).

* Compile the application by running `mvn clean compile dependency:copy-dependencies -DstripVersion`

* To try with a *replicated* cache, run the following command in separated terminals:
    * `java -cp target/classes:target/dependency/* org.infinispan.quickstart.clusteredcache.Node -r`
    * `java -cp target/classes:target/dependency/* org.infinispan.quickstart.clusteredcache.Node$1 -r`

* To try with a *distributed* cache, run the following command in separated terminals:
    * `java -cp target/classes:target/dependency/* org.infinispan.quickstart.clusteredcache.Node -d`
    * `java -cp target/classes:target/dependency/* org.infinispan.quickstart.clusteredcache.Node$1 -d`
    * `java -cp target/classes:target/dependency/* org.infinispan.quickstart.clusteredcache.Node$2 -d`
