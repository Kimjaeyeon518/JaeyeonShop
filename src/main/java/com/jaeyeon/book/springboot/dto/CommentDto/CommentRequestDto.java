package com.jaeyeon.book.springboot.dto.CommentDto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class CommentRequestDto {

    private Long userId;
    private Long boardId;
    private String content;
}
