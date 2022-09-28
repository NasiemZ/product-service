package com.example.produktmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ProduktMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProduktMicroServiceApplication.class, args);
    }

}
