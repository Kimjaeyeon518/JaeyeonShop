package com.jaeyeon.book.springboot.web;

import com.jaeyeon.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)    // 테스트를 진행할 때 JUnit에 내장된 실행자가 아닌 SpringRunner 실행자 사용
@WebMvcTest(controllers = HelloController.class)    // 여러 스프링 어노테이션 중, Web에 집중할 수 있는 어노테이션 (@Controller, @ControllerAdvice 등 사용 가능)
public class HelloControllerTest {

    @Autowired      // 빈 주입
    private MockMvc mvc;    // 스프링 MVC 테스트의 시작점으로, 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트 가능

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))      // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함
                .andExpect(status().isOk())         // mvc.perform의 결과를 HTTP Header의 Status를 통해 검증
                .andExpect(content().string(hello));    // mvc.perform의 결과를 응답 본문의 내용("hello")을 통해 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name", name)   // param : API 테스트할 때 사용될 요청 파라미터 (String만 가능)
                                                .param("amount", String.valueOf(amount)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(name)))    // JSON 응답값을 필드별로 검증
                    .andExpect(jsonPath("$.amount", is(amount)));

    }
}
