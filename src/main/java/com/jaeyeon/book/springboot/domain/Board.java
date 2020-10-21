package com.jaeyeon.book.springboot.domain;

import com.jaeyeon.book.springboot.dto.UserDto.UserResponseDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
@Entity         // 테이블과 링크될 클래스임을 나타냄
public class Board extends BaseTimeEntity {

    @Id @Column     // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // PK의 생성 규칙 : strategy = GenerationType.IDENTITY -> auto_increment
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<Comment>();

    @Column(length = 500, nullable = false)   // 테이블의 칼럼을 나타내며, 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨 ( 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용 )
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)        // 기본 타입은 Char 이므로 Text로 변경하기위해 @Column 어노테이션 사용
    private String content;

    public void setUser(User user) {
        this.user = user;
        user.getBoards().add(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setBoard(this);
    }
}
