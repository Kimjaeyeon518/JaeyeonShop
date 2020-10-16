package com.jaeyeon.book.springboot;

import com.jaeyeon.book.springboot.util.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

// @EnableJpaAuditing  // JPA Auditing 활성화
@EnableConfigurationProperties({FileUploadProperties.class})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

