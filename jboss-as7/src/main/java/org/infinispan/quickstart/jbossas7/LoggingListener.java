package org.infinispan.quickstart.jbossas7;

import javax.inject.Inject;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;

/**
 * An Infinispan listener that simply logs cache entries with a CDI component
 * 
 * @author Pete Muir
 * 
 */
@Listener
public class LoggingListener {
   
   @Inject
   private CacheEventsManager cacheEventsManager;

   @CacheEntryCreated
   public void observeAdd(CacheEntryCreatedEvent<?, ?> event) {
      cacheEventsManager.addEvent("Cache entry with key %s added in cache %s", event.getKey(), event.getCache());
   }

   @CacheEntryRemoved
   public void observeRemove(CacheEntryRemovedEvent<?, ?> event) {
      cacheEventsManager.addEvent("Cache entry with key %s removed in cache %s", event.getKey(), event.getCache());
   }

}
