import org.infinispan.manager.*;
import org.infinispan.*;

public class Quickstart {
   public static void main(String args[]) throws Exception {
      Cache<Object, Object> c = new DefaultCacheManager().getCache();
   }
}
