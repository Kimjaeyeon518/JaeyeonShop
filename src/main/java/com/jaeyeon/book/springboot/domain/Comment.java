package com.jaeyeon.book.springboot.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
@Entity         // 테이블과 링크될 클래스임을 나타냄
public class Comment extends BaseTimeEntity {

    @Id     // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // PK의 생성 규칙 : strategy = GenerationType.IDENTITY -> auto_increment
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "TEXT", nullable = false)        // 기본 타입은 Char 이므로 Text로 변경하기위해 @Column 어노테이션 사용
    private String content;

    public Comment(Comment comment) {
        this.board = comment.getBoard();
        this.user = comment.getUser();
        this.content = comment.getContent();
    }

    public void setUser(User user) {
        this.user = user;
        user.getComments().add(this);
    }

    public void setBoard(Board board) {
        this.board = board;
        board.getComments().add(this);
    }

}
