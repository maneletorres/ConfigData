package com.maneletorres.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Lab8ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab8ServerApplication.class, args);
    }

}