package com.stone.it.rcms.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author iMrJi
 * @Description TODO
 * @Version 1.0
 */
@Configuration
@ImportResource(locations = {"classpath*:config/*.services.xml"})
public class CxfServicesConfig {

}
