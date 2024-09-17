package com.reznikov.smartenergy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RefreshScope
@EnableBinding(Source.class)

public class SmartEnergyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartEnergyApplication.class, args);
    }

}
