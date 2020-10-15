package com.jaeyeon.book.springboot.dto.ProductDto;

import com.jaeyeon.book.springboot.domain.enums.ProductStatus;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto implements Serializable {

    private Long id;
    private String name;
    private String category;
    private String description;
    private Integer price;
    private Integer discount;
    private String productImg;
    private ProductStatus productStatus;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MainProductResponseDto implements Serializable {
        private Long id;
        private String name;
        private String category;
        private String productImg;
        private Integer price;
        private Integer discount;
        private ProductStatus productStatus;
    }

    @Getter
    @Builder
    @ToString
    public static class AdminProductResponseDto {
        private Long id;
        private String name;
        private String category;
        private String productImg;
        private Integer price;
        private Integer discount;
        private ProductStatus productStatus;
    }

    @Getter
    @Builder
    @ToString
    public static class AdminProductDetailResponseDto {
        private Long id;
        private String name;
        private String category;
        private String productImg;
        private Integer price;
        private Integer discount;
        private ProductStatus productStatus;
    }
}