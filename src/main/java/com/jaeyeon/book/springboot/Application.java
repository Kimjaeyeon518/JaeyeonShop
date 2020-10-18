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

    @Bean
    public CommandLineRunner mappingDemo(ProductRepository productRepository) {
        return args -> {
            Product p = new Product();
            p.setName("예쁜 코트");
            p.setProductImg("pants-2.png");
            p.setCategory("Outer");
            p.setDescription("제품 상세 설명");
            p.setPrice(500000);
            p.setDiscount(30);

            productRepository.save(p);

        };
    }
}

