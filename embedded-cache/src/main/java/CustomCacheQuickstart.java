import static org.infinispan.eviction.EvictionStrategy.*;

import org.infinispan.*;
import org.infinispan.config.*;
import org.infinispan.manager.*;

public class CustomCacheQuickstart {
   public static void main(String args[]) throws Exception {
      EmbeddedCacheManager manager = new DefaultCacheManager();
      manager.defineConfiguration("custom-cache", new Configuration().fluent()
    		  .eviction().strategy(LIRS).maxEntries(10)
			  .build());
      Cache<?, ?> c = manager.getCache("custom-cache");
   }
}
