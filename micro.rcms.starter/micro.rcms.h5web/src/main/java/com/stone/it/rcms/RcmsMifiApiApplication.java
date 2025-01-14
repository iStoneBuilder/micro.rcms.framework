package com.stone.it.rcms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 设备与平台交互接口
 * 
 * @author cj.stone
 * @Date 2024/12/30
 * @Desc
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.stone.it"})
public class RcmsMifiApiApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RcmsMifiApiApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Rcms Mifi Api Application Start now ........");
        SpringApplication.run(RcmsMifiApiApplication.class, args);
    }
}
