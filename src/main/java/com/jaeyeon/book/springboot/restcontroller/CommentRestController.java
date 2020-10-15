package com.jaeyeon.book.springboot.restcontroller;

import com.jaeyeon.book.springboot.dto.CommentDto.CommentRequestDto;
import com.jaeyeon.book.springboot.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public Long save(@RequestBody CommentRequestDto commentRequestDto) {

        return commentService.save(commentRequestDto);
    }

    @PutMapping("/comment/{commentId}")
    public Long update(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.update(commentId, commentRequestDto);
    }

    @DeleteMapping("/comment/{id}")
    public Long delete(@PathVariable Long id) {
        commentService.delete(id);
        return id;
    }
}
