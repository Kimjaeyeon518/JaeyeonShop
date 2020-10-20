package com.jaeyeon.book.springboot.repository;

import com.jaeyeon.book.springboot.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT c FROM Comment c WHERE c.board.id=:boardId")
    List<Comment> findAllByBoardId(Long boardId);

}
