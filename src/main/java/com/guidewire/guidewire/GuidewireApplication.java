package com.guidewire.guidewire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.guidewire.guidewire")
public class GuidewireApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuidewireApplication.class, args);
    }
}