package org.infinispan.quickstart.jbossas7;

import org.infinispan.cdi.OverrideDefault;
import org.infinispan.manager.CacheContainer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class Resources {

   @Produces
   @OverrideDefault
   @Resource(lookup="java:jboss/infinispan/demo")
   private CacheContainer container;

   @Inject @New
   private LoggingListener loggingListener;
   
   @PostConstruct
   void startup() {
      container.getCache().addListener(loggingListener);
   }
}
