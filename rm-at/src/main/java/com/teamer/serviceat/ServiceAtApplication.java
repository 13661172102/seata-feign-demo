package com.teamer.serviceat;

import com.alibaba.cloud.seata.rest.SeataRestTemplateAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude = SeataRestTemplateAutoConfiguration.class)
public class ServiceAtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAtApplication.class, args);
    }

}
