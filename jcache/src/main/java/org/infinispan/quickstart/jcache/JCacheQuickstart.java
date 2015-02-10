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
import javax.cache.configuration.MutableConfiguration;

public class JCacheQuickstart {
	
	public static void main(String args[]) throws Exception {
		final String key = "key";
		final String value = "value";
		
		Cache<String, String> cache = getJCacheInstance("my-cache");

		System.out.println("Value to store in the cache:" + value);
		cache.put(key, value); // Add a entry
		
		final String newValue = "newValue";
		final String storedValue = cache.getAndPut(key,newValue); // get old and update to new
		if ( ! value.equals(storedValue) )
			throw new IllegalStateException("Stored value does not match provided value !");
		final String newStoredValue = cache.get(key);
		if ( ! newValue.equals(newStoredValue) )
			throw new IllegalStateException("Stored value " + newStoredValue + " does not match expectation:" + newValue);
		
		if ( ! cache.containsKey(key) )// Validate the entry is now in the cache
			throw new IllegalStateException("Failed to add value[" + value + "] associated to key:[" + key +"].");

		
		if ( cache.putIfAbsent(key, value) )
			throw new IllegalStateException("Key/value was already added, it should not be absent!");

		if ( ! cache.replace(key, newStoredValue) )
			throw new IllegalStateException("Key " + key + " should be present - but entry was NOT updated !");
		
		
		System.out.println("Value retrieved from cache:" + cache.get(key));
		if ( ! cache.remove(key) ) 		// Remove the entry from the cache
			throw new IllegalStateException("Failed to remove value[" + value + "] associated to key:[" + key +"].");
		
		cache.clear();
		shutdownCache(cache);
	}
	
	public static <K,V> Cache<K,V> getJCacheInstance(final String cacheName) {
		return Caching.getCachingProvider().getCacheManager().createCache(cacheName, new MutableConfiguration <K,V>());		
	}
	
	public static <K,V> void shutdownCache(Cache<K,V> cache) {
		cache.close();
		if ( ! cache.isClosed() ) 
			throw new IllegalStateException("Cache is not properly closed !");
	}
}
