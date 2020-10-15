//package com.jaeyeon.book.springboot.web;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jaeyeon.book.springboot.domain.Board;
//import com.jaeyeon.book.springboot.repository.BoardRepository;
//import com.jaeyeon.book.springboot.domain.enums.Role;
//import com.jaeyeon.book.springboot.domain.User;
//import com.jaeyeon.book.springboot.dto.BoardDto.BoardSaveRequestDto;
//import com.jaeyeon.book.springboot.dto.BoardDto.BoardUpdateRequestDto;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.*;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class BoardApiControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private BoardRepository boardRepository;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mvc;
//
//    @Before
//    public void setup() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        boardRepository.deleteAll();
//    }
//
//    @Test
//    @WithMockUser(roles="USER")     // 인증된 USER 권한의 가짜 사용자를 만들어 사용
//    public void Posts_등록된다() throws Exception {
//        User u = new User();
//        u.setEmail("zzz");
//        u.setName("zz");
//        u.setPicture("");
//        u.setRole(Role.USER);
//        //given
//        String title = "title";
//        String content = "content";
//        BoardSaveRequestDto requestDto = BoardSaveRequestDto.builder()
//                                                            .title(title)
//                                                            .content(content)
//                                                            .user(u)
//                                                            .build();
//
//        String url = "http://localhost:" + port + "/api/v1/posts";
//
//        //when
//        mvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());
//
//        List<Board> all = boardRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(title);
//        assertThat(all.get(0).getContent()).isEqualTo(content);
//
//    }
//
//    @Test
//    @WithMockUser(roles="USER")
//    public void Posts_수정된다() throws Exception {
//        User u = new User();
//        u.setEmail("zzz");
//        u.setName("zz");
//        u.setPicture("");
//        u.setRole(Role.USER);
//        //given
//        Board savedBoard = boardRepository.save(Board.builder()
//                .title("title")
//                .content("content")
//                .user(u)
//                .build());
//
//        Long updateId = savedBoard.getId();
//        String expectedTitle = "title2";
//        String expectedContent = "content2";
//
//        BoardUpdateRequestDto requestDto = BoardUpdateRequestDto.builder()
//                .title(expectedTitle)
//                .content(expectedContent)
//                .build();
//
//        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
//
//        //when
//        mvc.perform(put(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());
//
//        //then
//        List<Board> all = boardRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
//        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
//
//    }
//}
