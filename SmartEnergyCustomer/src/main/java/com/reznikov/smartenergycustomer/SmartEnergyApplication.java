package com.reznikov.smartenergycustomer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient


public class SmartEnergyApplication {
    private  final Logger logger = LoggerFactory.getLogger(SmartEnergyApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SmartEnergyApplication.class, args);
    }


}
