package com.guidewire.guidewire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.guidewire.guidewire.repository")
public class GuidewireApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuidewireApplication.class, args);
    }
}