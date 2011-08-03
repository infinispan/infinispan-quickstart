package org.infinispan.examples.tutorial.clustered;

import static org.infinispan.config.Configuration.CacheMode.*;

import java.io.IOException;

import org.infinispan.config.*;
import org.infinispan.examples.tutorial.clustered.util.ClusterValidation;
import org.infinispan.manager.*;

@SuppressWarnings("unused")
public abstract class AbstractNode {
   
   private static EmbeddedCacheManager createCacheManagerProgramatically() {
      return new DefaultCacheManager(
         GlobalConfiguration.getClusteredDefault().fluent()
            .transport()
               .addProperty("configurationFile", "jgroups.xml")
            .build(), 
         new Configuration().fluent()
            .clustering()
               .mode(REPL_SYNC)
            .build()
         );
   }
   
   
   private static EmbeddedCacheManager createCacheManagerFromXml() throws IOException {
      return new DefaultCacheManager("infinispan.xml");
   }
   
   public static final int CLUSTER_SIZE = 2;

   private final EmbeddedCacheManager cacheManager;
   private final int nodeId;
   
   public AbstractNode(int nodeId) throws IOException {
      this.nodeId = nodeId;
      this.cacheManager = createCacheManagerProgramatically();
      // Uncomment to create cache from XML
      // this.cacheManager = createCacheManagerFromXml();
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
   
   protected int getNodeId()
   {
      return nodeId;
   }

}