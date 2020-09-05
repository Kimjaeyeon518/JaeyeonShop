package com.jaeyeon.book.springboot.web.dto.CommentDto;

import com.jaeyeon.book.springboot.domain.comment.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long id;
    private String content;
    private String author;

    public CommentResponseDto(Comment entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
