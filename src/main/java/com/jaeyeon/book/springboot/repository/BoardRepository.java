package com.jaeyeon.book.springboot.repository;

import com.jaeyeon.book.springboot.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BoardRepository extends JpaRepository<Board, Long>, JpaSpecificationExecutor<Board> {

    Page<Board> findAllByTitleContaining(Pageable pageable, String keyword);
}