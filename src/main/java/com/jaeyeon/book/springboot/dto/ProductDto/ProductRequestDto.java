package com.jaeyeon.book.springboot.dto.ProductDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class ProductRequestDto {

    private String name;
    private String category;
    private String description;
    private Integer price;
    private Integer discount;

}
