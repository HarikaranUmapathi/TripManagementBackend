package com.helloworld.tripmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TripManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripManagementSystemApplication.class, args);
    }

}
