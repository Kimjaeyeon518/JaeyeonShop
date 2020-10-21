package com.jaeyeon.book.springboot.repository;

import com.jaeyeon.book.springboot.domain.Board;
import com.jaeyeon.book.springboot.domain.Cart;
import com.jaeyeon.book.springboot.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Comment> {

    List<Cart> findAllByUserId(Long userId);

    @Query(value = "SELECT c.id FROM Cart c WHERE c.user.id=:userId AND c.product.id=:productId")
    Long findAllByUserIdAndProductId(Long userId, Long productId);

}