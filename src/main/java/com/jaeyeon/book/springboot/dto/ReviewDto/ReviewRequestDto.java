package com.jaeyeon.book.springboot.dto.ReviewDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class ReviewRequestDto {

    private Long userId;
    private Long productId;
    private String content;
    private int rate;
}
