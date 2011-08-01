import org.infinispan.manager.*;

public class Quickstart {
   public static void main(String args[]) throws Exception {
      EmbeddedCacheManager manager = new DefaultCacheManager();
      manager.getCache();
   }
}
