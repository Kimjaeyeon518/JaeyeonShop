package com.jaeyeon.book.springboot.config.auth;

import com.jaeyeon.book.springboot.domain.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity      // Spring Security 설정들을 활성화시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable 함
                .and()
                    .authorizeRequests()    // URL별 권한 관리를 설정하는 옵션의 시작점
                    .antMatchers("/addProduct", "/selectCategory").hasRole(Role.USER.name())    // USER 권한을 가진 사람만 가능
                    .anyRequest().permitAll()   // 설정한 값들 이외 나머지 URL들은 인증된 사용자(로그인한 사용자)들에게만 허용
                .and()
                    .logout()
                      .logoutSuccessUrl("/");      // 로그아웃 성공 시 "/" 주소로 이동
    }
}