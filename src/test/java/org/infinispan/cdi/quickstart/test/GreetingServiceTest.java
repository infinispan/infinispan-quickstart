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

import org.infinispan.Cache;
import org.infinispan.cdi.quickstart.GreetingService;
import org.infinispan.cdi.quickstart.config.GreetingCache;
import org.infinispan.cdi.quickstart.test.util.Deployments;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.cache.interceptor.CacheKey;
import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Kevin Pollet
 */
@RunWith(Arquillian.class)
public class GreetingServiceTest {

   @Deployment
   public static WebArchive deployment() {
      return Deployments.baseDeployment()
            .addClass(GreetingServiceTest.class);
   }

   @Inject
   @GreetingCache
   private Cache<CacheKey, String> greetingCache;

   @Inject
   private GreetingService greetingService;

   @Test
   public void testGreetingServiceCache() {
      String greet = greetingService.greet("Pete");

      assertEquals("Hello Pete :)", greet);
      assertEquals(1, greetingCache.size());
      assertTrue( greetingCache.values().contains("Hello Pete :)"));

      greet = greetingService.greet("Manik");

      assertEquals("Hello Manik :)", greet);
      assertEquals(2, greetingCache.size());
      assertTrue( greetingCache.values().contains("Hello Manik :)"));

      greetingService.greet("Pete");

      assertEquals(2, greetingCache.size());
   }
}
