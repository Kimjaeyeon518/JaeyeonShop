package com.jaeyeon.book.springboot.web.dto.CommentDto;

import com.jaeyeon.book.springboot.domain.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentListResponseDto {
    private Long id;
    private String author;
    private String content;
    private LocalDateTime modifiedDate;

    public CommentListResponseDto(Comment entity) {
        this.id = entity.getId();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate();
    }
}