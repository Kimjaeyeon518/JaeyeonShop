//package com.jaeyeon.book.springboot.domain;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Slf4j
//@Setter
//@Getter
//@NoArgsConstructor
//@Entity
//public class Question extends BaseTimeEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column
//    private String message;
//
//    @Column
//    private boolean answerState;
//
//    @Column
//    private Integer answerCount;
//
//    // 객체들 간의 관계
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    @JsonIgnore
//    private Product product;
//
//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<QuestionAnswer> answers;
//}
