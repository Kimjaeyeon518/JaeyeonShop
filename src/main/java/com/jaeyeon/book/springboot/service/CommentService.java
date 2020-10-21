package com.jaeyeon.book.springboot.service;

import com.jaeyeon.book.springboot.domain.Board;
import com.jaeyeon.book.springboot.domain.Comment;
import com.jaeyeon.book.springboot.domain.User;
import com.jaeyeon.book.springboot.dto.CommentDto.CommentRequestDto;
import com.jaeyeon.book.springboot.repository.BoardRepository;
import com.jaeyeon.book.springboot.repository.CommentRepository;
import com.jaeyeon.book.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(CommentRequestDto commentRequestDto) {

        Optional<User> user = userRepository.findById(commentRequestDto.getUserId());
        Optional<Board> board = boardRepository.findById(commentRequestDto.getBoardId());

        Comment comment = new Comment();
        comment.setBoard(board.get());
        comment.setUser(user.get());
        comment.setContent(commentRequestDto.getContent());

        return commentRepository.save(comment).getId();
    }

    @Transactional
    public Long update(Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + commentId));

        comment.setContent(commentRequestDto.getContent());

        System.out.println("####" + comment.modifiedDate);
        System.out.println("####" + comment.getCreatedDate());
        System.out.println("####" + comment.getModifiedDate());
        return commentRepository.save(comment).getId();
    }

    @Transactional
    public void delete (Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + id));

        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public Comment findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id=" + id));

        return comment;
    }

    @Transactional(readOnly = true)
    public List<Comment> findAllByBoardId(Long boardId) {
        return commentRepository.findAllByBoardId(boardId).stream()
                .map(Comment::new)
                .collect(Collectors.toList());
    }
}