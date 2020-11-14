//package com.jaeyeon.book.springboot.domain;
//
//import lombok.*;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.persistence.*;
//
//@Slf4j
//@Setter
//@Getter
//@NoArgsConstructor
//@Entity
//public class Review extends BaseTimeEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(columnDefinition = "TEXT")
//    private String content;
//
//    @Column
//    private int rate;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//}
