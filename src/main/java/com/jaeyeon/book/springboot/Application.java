package com.jaeyeon.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

// @EnableJpaAuditing  // JPA Auditing 활성화
@SpringBootApplication
public class Application {

    public static void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/the_js_path/**");
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

