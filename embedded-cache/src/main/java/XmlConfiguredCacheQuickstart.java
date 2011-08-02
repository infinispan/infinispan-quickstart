import org.infinispan.*;
import org.infinispan.manager.*;

public class XmlConfiguredCacheQuickstart {
   public static void main(String args[]) throws Exception {
	   Cache<?, ?> c = new DefaultCacheManager("infinispan.xml").getCache("xml-configured-cache");
   }
}
