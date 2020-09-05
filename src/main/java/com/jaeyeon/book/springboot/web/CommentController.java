package com.jaeyeon.book.springboot.web;

import com.jaeyeon.book.springboot.service.CommentService;
import com.jaeyeon.book.springboot.web.dto.CommentDto.CommentListResponseDto;
import com.jaeyeon.book.springboot.web.dto.CommentDto.CommentResponseDto;
import com.jaeyeon.book.springboot.web.dto.CommentDto.CommentSaveRequestDto;
import com.jaeyeon.book.springboot.web.dto.CommentDto.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/v1/posts/{id}/comment")
    public Long save(@RequestBody CommentSaveRequestDto requestDto) {

        return commentService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}/comment/{cid}")
    public Long update(@PathVariable Long id, @PathVariable Long cid, @RequestBody CommentUpdateRequestDto requestDto) {
        return commentService.update(cid, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}/comment/{cid}")
    public Long delete(@PathVariable Long id, @PathVariable Long cid) {
        commentService.delete(cid);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}/comment/{cid}")
    public CommentResponseDto findById(@PathVariable Long id, @PathVariable Long cid) {
        return commentService.findById(cid);
    }

    @GetMapping("/api/v1/posts/{id}/comment")
    public List<CommentListResponseDto> findAll(@PathVariable Long id) {
        return commentService.findAllDesc();
    }
}
