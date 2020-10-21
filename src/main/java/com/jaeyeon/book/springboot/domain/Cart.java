package com.jaeyeon.book.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Cart {

    @Id @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer productCount;

    // 객체들 간의 관계
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "product_order_id", referencedColumnName = "id")
    @JsonIgnore
    private ProductOrder productOrder;

    public Cart(Cart cart) {
        this.id = cart.getId();
        this.user = cart.getUser();
        this.product = cart.getProduct();
        this.count = cart.getCount();
    }

}
