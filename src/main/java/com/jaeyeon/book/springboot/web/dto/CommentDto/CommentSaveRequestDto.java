package com.jaeyeon.book.springboot.web.dto.CommentDto;

import com.jaeyeon.book.springboot.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {

    private String content;
    private String author;

    @Builder
    public CommentSaveRequestDto(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .author(author)
                .build();
    }
}
