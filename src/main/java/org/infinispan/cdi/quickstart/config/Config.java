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
package org.infinispan.cdi.quickstart.config;

import org.infinispan.cdi.Infinispan;
import org.infinispan.config.Configuration;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * This is the configuration class.
 *
 * @author Kevin Pollet <pollet.kevin@gmail.com> (C) 2011
 */
public class Config {

   /**
    * <p>This producer defines the greeting cache configuration.</p>
    *
    * <p>This cache will have:
    * <ul>
    *    <li>a maximum of 4 entries</li>
    *    <li>use the strategy FIFO for eviction</li>
    * </ul>
    * </p>
    *
    * @return the greeting cache configuration.
    */
   @GreetingCache
   @Infinispan("greeting-cache")
   @Produces
   public Configuration greetingCache() {
      return new Configuration().fluent()
            .eviction().strategy(EvictionStrategy.FIFO).maxEntries(4)
            .build();
   }

   /**
    * <p>This producer defines the cache manager used to retrieve the greeting cache.</p>
    *
    * <p>The default configuration of this cache manager defines that a cache entry will have a lifespan of 60000 ms.</p>
    *
    * @return the specific cache manager used for the greeting cache.
    */
   @GreetingCache
   @Produces
   @ApplicationScoped
   public EmbeddedCacheManager specificCacheManager() {
      return new DefaultCacheManager(new Configuration().fluent()
                                           .expiration().lifespan(60000l)
                                           .build());
   }
}
