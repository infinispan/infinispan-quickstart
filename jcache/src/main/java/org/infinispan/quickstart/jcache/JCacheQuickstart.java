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
package org.infinispan.quickstart.jcache;

import javax.cache.Cache;
import javax.cache.Caching;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.MutableConfiguration;

public class JCacheQuickstart {
	
	public static void main(String args[]) throws Exception {
		final String key = "key";
		final String value = "value";
		
		Cache<String, String> cache = getJCacheInstance();

		System.out.println("Value to store in the cache:" + value);
		cache.put(key,value); 	// Add a entry
		if ( ! cache.containsKey(key) )// Validate the entry is now in the cache
			throw new IllegalStateException("Failed to add value[" + value + "] associated to key:[" + key +"].");

		System.out.println("Value retrieved from cache:" + cache.get(key));
		if ( ! cache.remove(key) ) 		// Remove the entry from the cache
			throw new IllegalStateException("Failed to remove value[" + value + "] associated to key:[" + key +"].");
		
		cache.clear();
		shutdownCache(cache);
	}

	public static  <K,V> CompleteConfiguration <K,V> getCacheConfiguration() {
		return new MutableConfiguration <K,V>();
	}
	
	public static <K,V> Cache<K,V> getJCacheInstance() {
		final String cacheName = "my-cache";
		Caching.getCachingProvider().getCacheManager().createCache(cacheName, getCacheConfiguration());		
		return Caching.getCachingProvider().getCacheManager().getCache(cacheName);	  
	}
	
	public static <K,V> void shutdownCache(Cache<K,V> cache) {
		cache.close();
		if ( ! cache.isClosed() ) 
			throw new IllegalStateException("Cache is not properly closed !");
	}
}
