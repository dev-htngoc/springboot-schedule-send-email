package com.example.springbootscheduleemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootScheduleEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootScheduleEmailApplication.class, args);
    }

}
