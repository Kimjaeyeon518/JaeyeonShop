package com.jaeyeon.book.springboot.domain.posts;

import com.jaeyeon.book.springboot.domain.BaseTimeEntity;
import com.jaeyeon.book.springboot.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor      // 기본 생성자 자동 추가
@Entity         // 테이블과 링크될 클래스임을 나타냄
public class Posts extends BaseTimeEntity {

    @Id     // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // PK의 생성 규칙 : strategy = GenerationType.IDENTITY -> auto_increment
    private Long id;

    @Column(length = 500, nullable = false)   // 테이블의 칼럼을 나타내며, 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨 ( 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용 )
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)        // 기본 타입은 Char 이므로 Text로 변경하기위해 @Column 어노테이션 사용
    private String content;

    private String author;

    @Builder    // 해당 클래스의 빌더 패턴 클래스를 생성 -> 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
