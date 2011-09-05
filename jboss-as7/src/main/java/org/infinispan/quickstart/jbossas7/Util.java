package org.infinispan.quickstart.jbossas7;

import java.util.List;

public class Util {

   public static String asCommaSeparatedList(List<?> objects) {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < objects.size(); i++) {
         if (i != 0) 
            builder.append(", ");
         builder.append(objects.get(i));
      }
      return builder.toString();
   }
   
}
