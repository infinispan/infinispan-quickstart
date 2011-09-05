package org.infinispan.quickstart.jbossas7;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;

import org.infinispan.cdi.OverrideDefault;
import org.infinispan.manager.EmbeddedCacheManager;

@Singleton
public class Resources {

   @SuppressWarnings("unused")
   @Produces
   @OverrideDefault
   @Resource(lookup="java:jboss/infinispan/jboss-as7-quickstart")
   private static EmbeddedCacheManager container;
   
}
