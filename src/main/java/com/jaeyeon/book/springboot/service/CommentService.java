package com.jaeyeon.book.springboot.service;

import com.jaeyeon.book.springboot.domain.comment.Comment;
import com.jaeyeon.book.springboot.domain.comment.CommentRepository;
import com.jaeyeon.book.springboot.web.dto.CommentDto.CommentListResponseDto;
import com.jaeyeon.book.springboot.web.dto.CommentDto.CommentResponseDto;
import com.jaeyeon.book.springboot.web.dto.CommentDto.CommentSaveRequestDto;
import com.jaeyeon.book.springboot.web.dto.CommentDto.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Long save(CommentSaveRequestDto requestDto) {

        return commentRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, CommentUpdateRequestDto requestDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        comment.update(requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public CommentResponseDto findById(Long id) {
        Comment entity = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new CommentResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<CommentListResponseDto> findAllDesc() {
        return commentRepository.findAllDesc().stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
    }
}