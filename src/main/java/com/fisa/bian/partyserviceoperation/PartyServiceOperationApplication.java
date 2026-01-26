package com.fisa.bian.partyserviceoperation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PartyServiceOperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PartyServiceOperationApplication.class, args);
    }

}
