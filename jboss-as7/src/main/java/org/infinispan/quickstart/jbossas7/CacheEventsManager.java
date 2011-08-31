package org.infinispan.quickstart.jbossas7;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;


@ApplicationScoped
public class CacheEventsManager {
   
   @Produces @CacheEvents
   private List<String> events;
   
   public CacheEventsManager() {
      this.events = new ArrayList<String>();
   }
   
   public void addEvent(String format, Object... args) {
      this.events.add(String.format(format, args));
   }

}
