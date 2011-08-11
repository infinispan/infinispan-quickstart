package org.infinispan.quickstart.jbossas7;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;

@Singleton
@Startup
public class Resources {
   
   @Resource(lookup="java:jboss/infinispan/demo")
   private CacheContainer container;
   
   @Inject @New
   private LoggingListener loggingListener;
   
   @PostConstruct
   public void startup() {
      getCache().addListener(loggingListener);
   }
   
   @Produces @ApplicationScoped
   public Cache<Object, Object> getCache() {
      return this.container.getCache();
   }

}
