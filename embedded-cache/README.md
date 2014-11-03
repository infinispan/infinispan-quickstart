Embedded Cache Quickstart
=========================

This quickstart demonstrates *embedded cache* running on a *single node* in 
*Java SE*.

The example can be deployed using Maven from the command line or from your favourite IDE

For more information, including how to set up Maven or JBoss Tools in Eclipse, 
refer to the [Getting Started Guide](http://infinispan.org/docs/7.0.x/getting_started/getting_started.html#_using_infinispan_as_an_embedded_cache_in_java_se).

To run each main class, type 

`mvn compile exec:java -Dexec.mainClass="org.infinispan.quickstart.embeddedcache.Quickstart"`

`mvn compile exec:java -Dexec.mainClass="org.infinispan.quickstart.embeddedcache.DefaultCacheQuickstart"`

`mvn compile exec:java -Dexec.mainClass="org.infinispan.quickstart.embeddedcache.CustomCacheQuickstart"`

`mvn compile exec:java -Dexec.mainClass="org.infinispan.quickstart.embeddedcache.XmlConfiguredCacheQuickstart"`