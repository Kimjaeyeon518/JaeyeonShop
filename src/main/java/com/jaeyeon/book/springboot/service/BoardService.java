package com.jaeyeon.book.springboot.service;

import com.jaeyeon.book.springboot.domain.Board;
import com.jaeyeon.book.springboot.domain.User;
import com.jaeyeon.book.springboot.dto.BoardDto.BoardRequestDto;
import com.jaeyeon.book.springboot.repository.BoardRepository;
import com.jaeyeon.book.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(BoardRequestDto boardRequestDto) {

        Optional<User> user = userRepository.findById(boardRequestDto.getUserId());

        Board board = new Board();

        board.setUser(user.get());
        board.setTitle(boardRequestDto.getTitle());
        board.setContent(boardRequestDto.getContent());

        return boardRepository.save(board).getId();
    }

    @Transactional
    public Long update(Long boardId, BoardRequestDto boardRequestDto) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + boardId));

        board.setTitle(boardRequestDto.getTitle());
        board.setContent(boardRequestDto.getContent());

        return boardRepository.save(board).getId();
    }

    @Transactional
    public void delete (Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        boardRepository.delete(board);
    }

    @Transactional(readOnly = true)
    public Board findById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        return board;
    }

    @Transactional
    public Page<Board> getBoardList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "id")); // <- Sort 추가

        return boardRepository.findAll(pageable);
    }

    @Transactional
    public Page<Board> searchBoard(Pageable pageable, String keyword) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "id")); // <- Sort 추가

        return boardRepository.findAllByTitleContaining(pageable, keyword);
    }
}