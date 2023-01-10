package com.q7g.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
@EnableEurekaServer
public class CloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class, args);
        List<Integer> list = new LinkedList<>();
    }
}
