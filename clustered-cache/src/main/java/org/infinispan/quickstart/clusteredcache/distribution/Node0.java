package org.infinispan.quickstart.clusteredcache.distribution;

import org.infinispan.Cache;
import org.infinispan.quickstart.clusteredcache.util.LoggingListener;


public class Node0 extends AbstractNode {

   public static void main(String[] args) throws Exception {
      new Node0().run();
   }
   
   public void run() {
      Cache<String, String> cache = getCacheManager().getCache("Demo");

      // Add a listener so that we can see the puts to this node
      cache.addListener(new LoggingListener());

      waitForClusterToForm();
   }
   
   @Override
   protected int getNodeId() {
      return 0;
   }

}
