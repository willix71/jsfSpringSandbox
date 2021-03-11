package org.wipo.patentscope2.web.robots.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wipo.patentscope2.web.robots.dummy.DummyBean;

@ManagedBean
@RequestScoped
public class ApplicationBean {

   private static final Logger LOG = LoggerFactory.getLogger(ApplicationBean.class);
   
   @ManagedProperty(value = "#{dummyBean}")
   private transient DummyBean dummyBean;
   //public DummyBean getDummyBean() { return dummyBean; }
   public void setDummyBean(DummyBean dummyBean) { this.dummyBean = dummyBean; }

   public ApplicationBean() {
      LOG.info("Creating...");
   }

   @PostConstruct
   public void created() {
      LOG.info("dummyBean {}", dummyBean);
   }
   
   public String getTestString() {
      return dummyBean.getDummyString();
   }
}