package org.infinispan.quickstart.clusteredcache.replication;

import static org.infinispan.config.Configuration.CacheMode.*;

import java.io.IOException;

import org.infinispan.config.*;
import org.infinispan.manager.*;
import org.infinispan.quickstart.clusteredcache.util.ClusterValidation;

@SuppressWarnings("unused")
public abstract class AbstractNode {
   
   private static EmbeddedCacheManager createCacheManagerProgramatically() {
      return new DefaultCacheManager(
         GlobalConfiguration.getClusteredDefault()
            .fluent()
               .transport()
                  .addProperty("configurationFile", "jgroups.xml")
               .build(), 
         new Configuration()
            .fluent()
               .clustering()
                  .mode(REPL_SYNC)
               .build()
         );
   }
   
   
   private static EmbeddedCacheManager createCacheManagerFromXml() throws IOException {
      return new DefaultCacheManager("infinispan-replication.xml");
   }
   
   public static final int CLUSTER_SIZE = 2;

   private final EmbeddedCacheManager cacheManager;
   
   public AbstractNode() {
      this.cacheManager = createCacheManagerProgramatically();
      // Uncomment to create cache from XML
      // try {
      //    this.cacheManager = createCacheManagerFromXml();
      // } catch (IOException e) {
      //    throw new RuntimeException(e);
      // }
   }
   
   protected EmbeddedCacheManager getCacheManager() {
      return cacheManager;
   }
   
   protected void waitForClusterToForm() {
      // Wait for the cluster to form, erroring if it doesn't form after the
      // timeout
      if (!ClusterValidation.waitForClusterToForm(getCacheManager(), getNodeId(), CLUSTER_SIZE)) {
         throw new IllegalStateException("Error forming cluster, check the log");
      }
   }
   
   protected abstract int getNodeId();
   
}