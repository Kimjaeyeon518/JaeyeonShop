package com.jaeyeon.book.springboot.repository;

import com.jaeyeon.book.springboot.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Page<Cart> findAllByUserIdOrderByCreatedDateDesc(Long userId, Pageable pageable);

    List<Cart> findAllByUserId(Long userId);

    List<Cart> findAllByUserIdAndProductId(Long userId, Long productId);


}