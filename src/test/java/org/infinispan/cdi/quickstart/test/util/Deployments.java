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
package org.infinispan.cdi.quickstart.test.util;

import org.infinispan.cdi.quickstart.GreetingService;
import org.infinispan.cdi.quickstart.config.Config;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

import java.io.File;

/**
 * @author Kevin Pollet
 */
public final class Deployments {

   // Disable instantiation
   private Deployments() {
   }

   public static WebArchive baseDeployment() {
      return ShrinkWrap.create(WebArchive.class)
            .addPackage(Config.class.getPackage())
            .addPackage(GreetingService.class.getPackage())
            .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"), "beans.xml")
            .addAsLibraries(
                  DependencyResolvers.use(MavenDependencyResolver.class)
                        .loadReposFromPom("pom.xml")
                        .artifact("javax.cache:cache-api")
                        .artifact("org.infinispan:infinispan-cdi")
                        .artifact("org.infinispan:infinispan-core")
                        .resolveAs(GenericArchive.class)
            );
   }
}
