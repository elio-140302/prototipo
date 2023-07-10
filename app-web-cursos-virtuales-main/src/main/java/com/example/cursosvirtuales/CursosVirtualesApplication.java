package com.example.cursosvirtuales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CursosVirtualesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CursosVirtualesApplication.class, args);
    }
}
