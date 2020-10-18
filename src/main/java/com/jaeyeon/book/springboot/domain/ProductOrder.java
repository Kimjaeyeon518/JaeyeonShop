package com.jaeyeon.book.springboot.domain;

import com.jaeyeon.book.springboot.domain.enums.OrderStatus;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ProductOrder extends BaseTimeEntity  {

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