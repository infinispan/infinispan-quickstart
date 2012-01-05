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
package org.infinispan.quickstart.jbossas7;

import org.infinispan.AdvancedCache;
import org.infinispan.configuration.cache.CacheMode;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@SessionScoped
@Named
public class Controller implements Serializable {
   
   @Inject
   transient AdvancedCache<Object, Object> cache;
   
   private transient Object key;
   private transient Object value;
   private transient String owners;
   
   public Object getValue() {
      return value;
   }
   
   public void setValue(Object value) {
      this.value = value;
   }
   
   public void query() {
      if (key != null) {
         this.value = cache.get(key);
         this.owners = Util.asCommaSeparatedList(locate());
      }
   }
   
   public void add() {
      if (key != null  && value != null)
         cache.put(key, value);
   }
   
   public Object getKey() {
      return key;
   }
   
   public void setKey(Object key) {
      this.key = key;
   }
   
   public String getOwners() {
      return owners;
   }
   
   public List<?> locate() {
      if (cache.getCacheConfiguration().clustering().cacheMode() != CacheMode.LOCAL) {
         return cache.getDistributionManager().locate(key);
      } else {
         return Collections.singletonList("local");
      }
   }
   
   public String getSelf() {
      if (cache.getCacheConfiguration().clustering().cacheMode() != CacheMode.LOCAL)
         return cache.getCacheManager().getAddress().toString();
      else
         return "local cache";
   }
   
}
