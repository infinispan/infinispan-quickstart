/*
 * Copyright 2011 Kevin Pollet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.infinispan.cdi.quickstart;


import org.infinispan.cdi.quickstart.config.GreetingCache;
import org.infinispan.Cache;
import org.infinispan.eviction.EvictionStrategy;

import javax.cache.interceptor.CacheKey;
import javax.cache.interceptor.CacheRemoveAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/**
 * @author Kevin Pollet
 */
@Named
@ApplicationScoped
public class GreetingCacheManager {

   @Inject
   @GreetingCache
   private Cache<CacheKey, String> cache;

   public String getCacheName() {
      return cache.getName();
   }

   public int getNumberOfEntries() {
      return cache.size();
   }

   public EvictionStrategy getEvictionStrategy() {
      return cache.getConfiguration().getEvictionStrategy();
   }

   public int getEvictionMaxEntries() {
      return cache.getConfiguration().getEvictionMaxEntries();
   }

   public String[] getCachedValues() {
      Collection<String> cachedValues = cache.values();
      return cachedValues.toArray(new String[cachedValues.size()]);
   }

   @CacheRemoveAll(cacheName = "greeting-cache")
   public void clearCache() {
   }
}
