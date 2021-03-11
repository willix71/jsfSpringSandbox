package org.wipo.patentscope2.web.robots.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wipo.patentscope2.web.robots.dummy.DummyBean;

@Configuration
public class RobotsApplicationContext {

   private static final Logger LOG = LoggerFactory.getLogger(RobotsApplicationContext.class);
   
   public RobotsApplicationContext() {
      LOG.info("RobotsApplicationContext created");
   }

   @Bean
   public DummyBean dummyBean() {
      return new DummyBean();
   }

}
