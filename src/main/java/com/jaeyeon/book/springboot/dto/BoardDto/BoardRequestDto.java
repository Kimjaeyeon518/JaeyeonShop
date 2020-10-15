package com.jaeyeon.book.springboot.dto.BoardDto;

import com.jaeyeon.book.springboot.domain.Board;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class BoardRequestDto {

    private Long userId;
    private String title;
    private String content;

}
