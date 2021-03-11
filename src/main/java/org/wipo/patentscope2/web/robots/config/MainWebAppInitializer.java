package org.wipo.patentscope2.web.robots.config;

import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.sun.faces.config.FacesInitializer;

public class MainWebAppInitializer extends FacesInitializer implements WebApplicationInitializer {

   private static final Logger LOG = LoggerFactory.getLogger(MainWebAppInitializer.class);

   private Properties versionProperties = new Properties();
   
   public MainWebAppInitializer() {
      LOG.info("Configuring SLF4JBridgeHandler for java.util.logging ");
      
      // Remove existing handlers attached to j.u.l root logger
      SLF4JBridgeHandler.removeHandlersForRootLogger(); 

      // add SLF4JBridgeHandler to j.u.l's root logger 
      // should be done once during the initialization phase of your application
      SLF4JBridgeHandler.install();
 
      
      try {
         ClassLoader cl = Thread.currentThread().getContextClassLoader();
         InputStream is = cl.getResourceAsStream("version.properties");
         if (is!=null) {
            versionProperties.load(is);
            is.close();
         }
      } catch(Exception e) {
         LOG.error("Failed to load resource version.properties", e);
      }
   }
   
   @Override // not sure this is ever called...
   public void onStartup(Set<Class<?>> classes, ServletContext servletContext) throws ServletException {
      super.onStartup(classes, servletContext);     
   }

   /**
    * Register and configure all Servlet container components necessary to power the web application.
    */
   @Override
   public void onStartup(final ServletContext sc) throws ServletException {
      String text = String.format("Web application starting with version %s", versionProperties.getProperty("version"));
      String emphasizer = new String(new char[text.length()]).replace('\0','*');
      LOG.info("\n{}\n{}\n{}", emphasizer, text ,emphasizer);
      
      sc.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
      sc.setInitParameter("primefaces.FONT_AWESOME", "true");

      // Create the 'root' Spring application context
      final AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
      root.register(RobotsApplicationContext.class);
      
      /*
       * <listener>
       *   <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
       * </listener>
       */
      sc.addListener(new ContextLoaderListener(root));
   }

}
