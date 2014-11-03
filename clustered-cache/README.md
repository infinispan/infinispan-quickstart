Clustered Cache Quickstart
==========================

This quickstart demonstrates *embedded cache* running on *two node* in 
*Java SE*.

The example can be deployed using Maven from the command line or from your favourite IDE

For more information, including how to set up Maven or JBoss Tools in Eclipse, 
refer to the [Getting Started Guide](https://docs.jboss.org/author/display/ISPN/Getting+Started+Guide+-+Clustered+Cache+in+Java+SE).

* To try with a *replicated* cache, run the following command in separated terminals:
    * `mvn compile exec:java -Djava.net.preferIPv4Stack=true  -Dexec.mainClass="org.infinispan.quickstart.clusteredcache.Node" -Dexec.args="-r A"`
    * `mvn compile exec:java -Djava.net.preferIPv4Stack=true  -Dexec.mainClass="org.infinispan.quickstart.clusteredcache.Node" -Dexec.args="-r B"`

* To try with a *distributed* cache, run the following command in separated terminals:
    * `mvn compile exec:java -Djava.net.preferIPv4Stack=true  -Dexec.mainClass="org.infinispan.quickstart.clusteredcache.Node" -Dexec.args="-d A"`
    * `mvn compile exec:java -Djava.net.preferIPv4Stack=true  -Dexec.mainClass="org.infinispan.quickstart.clusteredcache.Node" -Dexec.args="-d B"`
    * `mvn compile exec:java -Djava.net.preferIPv4Stack=true  -Dexec.mainClass="org.infinispan.quickstart.clusteredcache.Node" -Dexec.args="-d C"`