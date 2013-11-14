Embedded Cache Quickstart
=========================

This quickstart demonstrates *embedded cache* running on a *single node* in 
*Java SE*.

The example can be deployed using Maven from the command line or from Eclipse using
JBoss Tools.

For more information, including how to set up Maven or JBoss Tools in Eclipse, 
refer to the [Getting Started Guide](https://docs.jboss.org/author/display/ISPN/Getting+Started+Guide+-+Embedded+Cache+in+Java+SE).

To compile, type `mvn clean compile dependency:copy-dependencies -DstripVersion`, 
and then, to run, `java -cp target/classes:target/dependency/* org.infinispan.quickstart.embeddedcache.Quickstart`.
