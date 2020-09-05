package com.jaeyeon.book.springboot.web.dto.CommentDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentUpdateRequestDto {

    private String content;

    @Builder
    public CommentUpdateRequestDto(String title, String content) {
        this.content = content;
    }
}
