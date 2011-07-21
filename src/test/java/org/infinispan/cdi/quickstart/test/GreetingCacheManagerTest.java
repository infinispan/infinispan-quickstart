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
package org.infinispan.cdi.quickstart.test;

import org.infinispan.cdi.quickstart.GreetingCacheManager;
import org.infinispan.cdi.quickstart.GreetingService;
import org.infinispan.cdi.quickstart.test.util.Deployments;
import org.infinispan.eviction.EvictionStrategy;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * @author Kevin Pollet
 */
@RunWith(Arquillian.class)
public class GreetingCacheManagerTest {

   @Deployment
   public static WebArchive deployment() {
      return Deployments.baseDeployment()
            .addClass(GreetingCacheManagerTest.class);
   }

   @Inject
   private GreetingService greetingService;

   @Inject
   private GreetingCacheManager cacheManager;

   @Test
   public void testGreetingCacheManager() {
      assertEquals("greeting-cache", cacheManager.getCacheName());
      assertEquals(EvictionStrategy.FIFO, cacheManager.getEvictionStrategy());
      assertEquals(4, cacheManager.getEvictionMaxEntries());

      greetingService.greet("Pete");

      assertEquals(1, cacheManager.getNumberOfEntries());

      cacheManager.clearCache();

      assertEquals(0, cacheManager.getNumberOfEntries());
   }
}
