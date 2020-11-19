package com.jaeyeon.book.springboot;

import com.jaeyeon.book.springboot.domain.Product;
import com.jaeyeon.book.springboot.repository.ProductRepository;
import com.jaeyeon.book.springboot.util.FileUploadProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

// @EnableJpaAuditing  // JPA Auditing 활성화
@EnableConfigurationProperties({FileUploadProperties.class})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

