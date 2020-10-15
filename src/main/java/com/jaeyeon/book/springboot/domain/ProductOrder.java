package com.jaeyeon.book.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jaeyeon.book.springboot.domain.enums.OrderStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
@Entity
public class ProductOrder extends BaseTimeEntity {

    @Id     // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String orderNumber;

    @Column
    private String orderName;

    @Column
    private String deliveryMessage;

    @Column
    private OrderStatus orderStatus;

    @Column
    private String address;

    @Column
    private Character refundState;

    @Column
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL)
    private List<Cart> carts;

}
