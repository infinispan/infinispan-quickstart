package org.infinispan.quickstart.jbossas7;

import org.infinispan.cdi.OverrideDefault;
import org.infinispan.manager.EmbeddedCacheManager;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class Resources {

   @Produces
   @OverrideDefault
   @Resource(lookup="java:jboss/infinispan/jboss-as7-quickstart")
   private EmbeddedCacheManager container;

   @Inject @New
   private LoggingListener loggingListener;
   
   @PostConstruct
   private void startup() {
      container.getCache().addListener(loggingListener);
   }
}
