package com.jaeyeon.book.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jaeyeon.book.springboot.domain.enums.ProductStatus;
import com.jaeyeon.book.springboot.dto.ProductDto.ProductResponseDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity         // 테이블과 링크될 클래스임을 나타냄
public class Product extends BaseTimeEntity {

    @Id     // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // PK의 생성 규칙 : strategy = GenerationType.IDENTITY -> auto_increment
    private Long id;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<Question>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cart> carts = new ArrayList<Cart>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews = new ArrayList<Review>();

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    private String name;
    private String category;
    private String productImg;
    private String description;
    private Integer price;
    private Integer discount;

}
