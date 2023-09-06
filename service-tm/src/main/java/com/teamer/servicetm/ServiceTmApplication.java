package com.teamer.servicetm;

import com.alibaba.cloud.seata.rest.SeataRestTemplateAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 事务管理器 TM ，用于发起事务
 *
 * @author tanzj
 */
@EnableFeignClients
@SpringBootApplication(exclude = SeataRestTemplateAutoConfiguration.class)
public class ServiceTmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTmApplication.class, args);
    }


}
