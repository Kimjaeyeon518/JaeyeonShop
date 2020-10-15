//package com.jaeyeon.book.springboot.domain.enums;
//
//import com.jaeyeon.book.springboot.domain.Board;
//import com.jaeyeon.book.springboot.domain.User;
//import com.jaeyeon.book.springboot.repository.UserRepository;
//import com.jaeyeon.book.springboot.repository.BoardRepository;
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class BoardRepositoryTest {
//
//    @Autowired
//    BoardRepository boardRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @After      // Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드
//    public void cleanup() {
//        boardRepository.deleteAll();
//    }
//
//    @Test
//    public void 게시글저장_불러오기() {
//        //given
//        String title = "테스트 게시글";
//        String content = "테스트 본문";
//        User u = new User();
//        u.setEmail("zzz");
//        u.setName("zz");
//        u.setPicture("");
//        u.setRole(Role.USER);
//        userRepository.save(u);
//        boardRepository.save(Board.builder()    // 테이블 posts에 insert/update 쿼리를 실행 -> id값이 있다면 update, 없으면 insert 실행
//                .title(title)
//                .content(content)
//                .user(u)
//                .build());
//
//        //when
//        List<Board> boardList = boardRepository.findAll();  // 테이블 posts에 있는 모든 데이터를 조회해오는 메소드
//
//        //then
//        Board board = boardList.get(0);
//
//        assertThat(board.getTitle()).isEqualTo(title);
//        assertThat(board.getContent()).isEqualTo(content);
//
//    }
//
//    @Test
//    public void BaseTimeEntity_등록() {
//        User u = new User();
//
//        u.setEmail("zzz");
//        u.setName("zz");
//        u.setPicture("");
//        u.setRole(Role.USER);
//
//        userRepository.save(u);
//
//        //given
//        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
//        boardRepository.save(Board.builder()
//                .title("title")
//                .content("content")
//                .user(u)
//                .build());
//        //when
//        List<Board> boardList = boardRepository.findAll();
//
//        //then
//        Board board = boardList.get(0);
//
//        System.out.println(">>>>>>>>> createDate=" + board.getCreatedDate() + ", modifiedDate=" + board.getModifiedDate());
//
//        assertThat(board.getCreatedDate()).isAfter(now);
//        assertThat(board.getModifiedDate()).isAfter(now);
//    }
//}
