package org.infinispan.quickstart.clusteredcache.replication;

import org.infinispan.Cache;
import org.infinispan.quickstart.clusteredcache.util.LoggingListener;
import org.infinispan.util.logging.Log;
import org.infinispan.util.logging.LogFactory;

public class Node1 extends AbstractNode {

   private Log log = LogFactory.getLog(LoggingListener.class);
   
   public static void main(String[] args) throws Exception {
      new Node1().run();
   }

   public void run() {
      Cache<String, String> cache = getCacheManager().getCache("Demo");

      waitForClusterToForm();
      
      log.info("About to put key, value into cache on node " + getNodeId());
      // Put some information in the cache that we can display on the other node
      cache.put("key", "value");
   }
   
   @Override
   protected int getNodeId() {
      return 1;
   }

}
