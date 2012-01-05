/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other
 * contributors as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.infinispan.quickstart.embeddedcache;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.infinispan.quickstart.embeddedcache.util.Assert.assertEqual;
import static org.infinispan.quickstart.embeddedcache.util.Assert.assertFalse;
import static org.infinispan.quickstart.embeddedcache.util.Assert.assertTrue;

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
