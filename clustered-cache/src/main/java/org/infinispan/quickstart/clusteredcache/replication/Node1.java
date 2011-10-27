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
package org.infinispan.quickstart.clusteredcache.replication;

import org.infinispan.Cache;
import org.infinispan.quickstart.clusteredcache.util.LoggingListener;
import org.infinispan.util.logging.Log;
import org.infinispan.util.logging.LogFactory;

public class Node1 extends AbstractNode {

   private Log log = LogFactory.getLog(LoggingListener.class);
   
   public static void main(String[] args) throws Exception {
      new Node1().run();
   }

   public void run() {
      Cache<String, String> cache = getCacheManager().getCache("Demo");

      waitForClusterToForm();
      
      log.info("About to put key, value into cache on node " + getNodeId());
      // Put some information in the cache that we can display on the other node
      cache.put("key", "value");
   }
   
   @Override
   protected int getNodeId() {
      return 1;
   }

}
