package com.jaeyeon.book.springboot.repository;

import com.jaeyeon.book.springboot.domain.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FileRepository extends JpaRepository<FileModel, Long> {
    FileModel findByName(String name);
}