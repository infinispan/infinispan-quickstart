package org.infinispan.quickstart.jbossas7;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infinispan.AdvancedCache;
import org.infinispan.config.Configuration.CacheMode;

@SessionScoped
@Named
public class Controller implements Serializable {
   
   @Inject
   AdvancedCache<Object, Object> cache;
   
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
      if (cache.getConfiguration().getCacheMode() != CacheMode.LOCAL) {
         return cache.getDistributionManager().locate(key);
      } else {
         return Collections.singletonList("local");
      }
   }
   
   public String getSelf() {
      if (cache.getConfiguration().getCacheMode() != CacheMode.LOCAL)
         return cache.getCacheManager().getAddress().toString();
      else
         return "local cache";
   }
   
}
