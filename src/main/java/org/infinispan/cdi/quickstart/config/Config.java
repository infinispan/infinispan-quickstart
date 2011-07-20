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
package org.infinispan.cdi.quickstart.config;

import org.infinispan.cdi.Infinispan;
import org.infinispan.config.Configuration;
import org.infinispan.eviction.EvictionStrategy;

import javax.enterprise.inject.Produces;

/**
 * @author Kevin Pollet
 */
public class Config {

   @GreetingCache
   @Infinispan("greeting-cache")
   @Produces
   public Configuration greetingCache() {
      Configuration configuration = new Configuration();
      configuration.fluent()
            .eviction()
            .strategy(EvictionStrategy.FIFO)
            .maxEntries(4);

      return configuration;
   }
}
