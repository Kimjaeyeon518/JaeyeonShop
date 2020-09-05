package com.jaeyeon.book.springboot.domain.comment;

import com.jaeyeon.book.springboot.domain.BaseTimeEntity;
import com.jaeyeon.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor      // 기본 생성자 자동 추가
@Entity         // 테이블과 링크될 클래스임을 나타냄
public class Comment extends BaseTimeEntity {
//
//    @ManyToOne   //다대일(댓글과 게시글은 N:1 관계)
//    @JoinColumn(name = "pid")  //조인컬럼은 외래키를 매핑할때 사용 (연관관계주인)
//    private Posts posts;  //연관관계 주인 필드

    @Id     // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // PK의 생성 규칙 : strategy = GenerationType.IDENTITY -> auto_increment
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)        // 기본 타입은 Char 이므로 Text로 변경하기위해 @Column 어노테이션 사용
    private String content;

    private String author;

    @Builder    // 해당 클래스의 빌더 패턴 클래스를 생성 -> 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Comment(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public void update(String content) {
        this.content = content;
    }

}
