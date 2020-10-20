package com.jaeyeon.book.springboot.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class CartRequestDto {
    private Long userId;
    private Long productId;
    private Integer totalPrice;
    private Integer totalDiscountPrice;
}
