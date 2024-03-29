package com.stone.it.rcms.scheduler.core.factory;

import javax.annotation.Resource;
import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author cj.stone
 * @Desc
 */
@Component
public class JobFactory extends AdaptableJobFactory {

  private static final Logger LOGGER = LoggerFactory.getLogger(JobFactory.class);
  
  @Resource
  private AutowireCapableBeanFactory capableBeanFactory;

  @Override
  protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
    LOGGER.info("创建Quartz Job实例工厂 ...");
    //调用父类的方法
    Object jobInstance = super.createJobInstance(bundle);
    //进行注入
    capableBeanFactory.autowireBean(jobInstance);
    return jobInstance;
  }
}