package com.jaeyeon.book.springboot.restcontroller;

import com.jaeyeon.book.springboot.dto.BoardDto.BoardRequestDto;
import com.jaeyeon.book.springboot.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardRestController {

    private final BoardService boardService;

    @PostMapping("/board")
    public Long save(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.save(boardRequestDto);
    }

    @PutMapping("/board/{boardId}")
    public Long update(@PathVariable Long boardId, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.update(boardId, boardRequestDto);
    }

    @DeleteMapping("/board/{id}")
    public Long delete(@PathVariable Long id) {
        boardService.delete(id);
        return id;
    }
}