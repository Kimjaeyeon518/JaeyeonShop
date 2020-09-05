package com.jaeyeon.book.springboot.domain.posts;

import com.jaeyeon.book.springboot.domain.comment.Comment;
import com.jaeyeon.book.springboot.domain.comment.CommentRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @After      // Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드
    public void cleanup() {
        commentRepository.deleteAll();
    }

    @Test
    public void 댓글저장_불러오기() {

        //given
        String content = "테스트 본문";

        commentRepository.save(Comment.builder()
                .content(content)
                .author("jae518")
                .build());

        //when
        List<Comment> commentList = commentRepository.findAll();  // 테이블 posts에 있는 모든 데이터를 조회해오는 메소드

        //then
        Comment comment = commentList.get(0);

        assertThat(comment.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        commentRepository.save(Comment.builder()
                .content("content")
                .author("author")
                .build());
        //when
        List<Comment> commentList = commentRepository.findAll();

        //then
        Comment comment = commentList.get(0);

        System.out.println(">>>>>>>>> createDate=" + comment.getCreatedDate() + ", modifiedDate=" + comment.getModifiedDate());

        assertThat(comment.getCreatedDate()).isAfter(now);
        assertThat(comment.getModifiedDate()).isAfter(now);
    }
}
