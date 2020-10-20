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
            p.setName("예쁜 바지");
            p.setProductImg("pants-2.png");
            p.setCategory("Outer");
            p.setDescription("제품 상세 설명");
            p.setPrice(500000);
            p.setDiscount(30);

            productRepository.save(p);

            Product a = new Product();
            a.setName("예쁜 바지들");
            a.setProductImg("pants-1.png");
            a.setCategory("Outer");
            a.setDescription("제품 상세 설명");
            a.setPrice(180000);
            a.setDiscount(30);

            productRepository.save(a);

            Product v = new Product();
            v.setName("예쁜 코트");
            v.setProductImg("clothing-2.png");
            v.setCategory("Outer");
            v.setDescription("제품 상세 설명");
            v.setPrice(230000);
            v.setDiscount(30);

            productRepository.save(v);

            Product q = new Product();
            q.setName("예쁜 상의");
            q.setProductImg("clothing-2.png");
            q.setCategory("Outer");
            q.setDescription("제품 상세 설명");
            q.setPrice(230000);
            q.setDiscount(30);

            productRepository.save(q);
        };
    }
}

