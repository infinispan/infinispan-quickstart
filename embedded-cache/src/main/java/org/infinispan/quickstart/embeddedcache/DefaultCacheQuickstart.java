package org.infinispan.quickstart.embeddedcache;

import static java.util.concurrent.TimeUnit.*;
import static org.infinispan.quickstart.embeddedcache.util.Assert.*;

import org.infinispan.*;
import org.infinispan.manager.*;

public class DefaultCacheQuickstart {
   public static void main(String args[]) throws Exception {
	   Cache<Object, Object> cache = new DefaultCacheManager().getCache();
      
      // Add a entry
      cache.put("key", "value");
      // Validate the entry is now in the cache
      assertEqual(1, cache.size());
      assertTrue(cache.containsKey("key"));
      // Remove the entry from the cache
      Object v = cache.remove("key");
      // Validate the entry is no longer in the cache
      assertEqual("value", v);
      assertTrue(cache.isEmpty());

      // Add an entry with the key "key"
      cache.put("key", "value");
      // And replace it if missing
      cache.putIfAbsent("key", "newValue");
      // Validate that the new value was not added
      assertEqual("value", cache.get("key"));

      cache.clear();
      assertTrue(cache.isEmpty());

      //By default entries are immortal but we can override this on a per-key basis and provide lifespans.
      cache.put("key", "value", 5, SECONDS);
      assertTrue(cache.containsKey("key"));
      Thread.sleep(10000);
      assertFalse(cache.containsKey("key"));
      
   }
}
