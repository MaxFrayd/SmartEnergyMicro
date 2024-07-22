package com.reznikov.smartenergycustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SmartEnergyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartEnergyApplication.class, args);
    }

}
