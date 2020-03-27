package com.hours22.devstudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class DevstudentApplication {
    public static void main(String[] args) {
        SpringApplication.run(DevstudentApplication.class, args);
    }
}
