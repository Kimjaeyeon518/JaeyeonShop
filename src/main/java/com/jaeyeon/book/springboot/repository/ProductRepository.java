package com.jaeyeon.book.springboot.repository;

import com.jaeyeon.book.springboot.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long>  {

    @Query(value = "SELECT p FROM Product p WHERE p.category=:category ORDER BY p.id DESC")
    Page<Product> findAllByCategory(Pageable pageable, @Param("category") String category);

    Page<Product> findAllByNameContaining(Pageable pageable, String keyword);
}
