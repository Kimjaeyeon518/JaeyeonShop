package com.jaeyeon.book.springboot.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Comment> findAllDesc();

}
