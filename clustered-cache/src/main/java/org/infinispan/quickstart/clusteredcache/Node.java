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
package org.infinispan.quickstart.clusteredcache;

import org.infinispan.Cache;
import org.infinispan.commons.logging.BasicLogFactory;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.quickstart.clusteredcache.util.LoggingListener;
import org.jboss.logging.BasicLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Node {

   private static final BasicLogger log = BasicLogFactory.getLog(Node.class);

   private final boolean useXmlConfig;
   private final String cacheName;
   private final String nodeName;
   private volatile boolean stop = false;

   public Node(boolean useXmlConfig, String cacheName, String nodeName) {
      this.useXmlConfig = useXmlConfig;
      this.cacheName = cacheName;
      this.nodeName = nodeName;
   }

   public static void main(String[] args) throws Exception {
      boolean useXmlConfig = false;
      String cache = "repl";
      String nodeName = null;

      for (String arg : args) {
         if ("-x".equals(arg)) {
            useXmlConfig = true;
         } else if ("-p".equals(arg)) {
            useXmlConfig = false;
         } else if ("-d".equals(arg)) {
            cache = "dist";
         } else if ("-r".equals(arg)) {
            cache = "repl";
         } else {
            nodeName = arg;
         }
      }
      new Node(useXmlConfig, cache, nodeName).run();
   }
   
   public void run() throws IOException, InterruptedException {
      EmbeddedCacheManager cacheManager = createCacheManager();
      final Cache<String, String> cache = cacheManager.getCache(cacheName);
      System.out.printf("Cache %s started on %s, cache members are now %s\n", cacheName, cacheManager.getAddress(),
            cache.getAdvancedCache().getRpcManager().getMembers());

      // Add a listener so that we can see the puts to this node
      cache.addListener(new LoggingListener());

      printCacheContents(cache);

      Thread putThread = new Thread() {
         @Override
         public void run() {
            int counter = 0;
            while (!stop) {
               try {
                  cache.put("key-" + counter, "" + cache.getAdvancedCache().getRpcManager().getAddress() + "-" + counter);
               } catch (Exception e) {
                  log.warnf("Error inserting key into the cache", e);
               }
               counter++;

               try {
                  Thread.sleep(1000);
               } catch (InterruptedException e) {
                  break;
               }
            }
         }
      };
      putThread.start();

      System.out.println("Press Enter to print the cache contents, Ctrl+D/Ctrl+Z to stop.");
      while (System.in.read() > 0) {
         printCacheContents(cache);
      }

      stop = true;
      putThread.join();
      cacheManager.stop();
      System.exit(0);
   }

   private void printCacheContents(Cache<String, String> cache) {
      System.out.printf("Cache contents on node %s\n", cache.getAdvancedCache().getRpcManager().getAddress());

      ArrayList<Map.Entry<String, String>> entries = new ArrayList<Map.Entry<String, String>>(cache.entrySet());
      entries.sort(new Comparator<Map.Entry<String, String>>() {
         @Override
         public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
            return o1.getKey().compareTo(o2.getKey());
         }
      });
      for (Map.Entry<String, String> e : entries) {
         System.out.printf("\t%s = %s\n", e.getKey(), e.getValue());
      }
      System.out.println();
   }

   private EmbeddedCacheManager createCacheManager() throws IOException {
      if (useXmlConfig) {
         return createCacheManagerFromXml();
      } else {
         return createCacheManagerProgrammatically();
      }
   }

   private EmbeddedCacheManager createCacheManagerProgrammatically() {
      System.out.println("Starting a cache manager with a programmatic configuration");
      DefaultCacheManager cacheManager = new DefaultCacheManager(
            GlobalConfigurationBuilder.defaultClusteredBuilder()
                  .transport().nodeName(nodeName).addProperty("configurationFile", "jgroups.xml")
                  .build(),
            new ConfigurationBuilder()
                  .clustering()
                  .cacheMode(CacheMode.REPL_SYNC)
                  .build()
      );
      // The only way to get the "repl" cache to be exactly the same as the default cache is to not define it at all
      cacheManager.defineConfiguration("dist", new ConfigurationBuilder()
            .clustering()
            .cacheMode(CacheMode.DIST_SYNC)
            .hash().numOwners(2)
            .build()
      );
      return cacheManager;
   }

   private EmbeddedCacheManager createCacheManagerFromXml() throws IOException {
      System.out.println("Starting a cache manager with an XML configuration");
      System.setProperty("nodeName", nodeName);
      return new DefaultCacheManager("infinispan.xml");
   }

}
