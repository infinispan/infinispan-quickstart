package org.infinispan.quickstart.clusteredcache.distribution;

import org.infinispan.Cache;
import org.infinispan.quickstart.clusteredcache.util.LoggingListener;

public class Node2 extends AbstractNode {

   public static void main(String[] args) throws Exception {
      new Node2().run();
   }

   public void run() {
      Cache<String, String> cache = getCacheManager().getCache("Demo");

      waitForClusterToForm();

      // Add a listener so that we can see the puts to this node
      cache.addListener(new LoggingListener());
      
      // Put a few entries into the cache so that we can see them distribution to the other nodes
      for (int i = 0; i < 20; i++)
         cache.put("key" + i, "value" + i);
   }
   
   @Override
   protected int getNodeId() {
      return 2;
   }

}
