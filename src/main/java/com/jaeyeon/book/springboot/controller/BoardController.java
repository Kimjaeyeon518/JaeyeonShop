package com.jaeyeon.book.springboot.controller;

import com.jaeyeon.book.springboot.config.auth.LoginUser;
import com.jaeyeon.book.springboot.config.auth.dto.SessionUser;
import com.jaeyeon.book.springboot.domain.Board;
import com.jaeyeon.book.springboot.service.CommentService;
import com.jaeyeon.book.springboot.service.BoardService;
import com.jaeyeon.book.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final UserService userService;

    @GetMapping("/boardList")        // 이제 어느 컨트롤러든지 @LoginUser만 사용하면 세션정보를 가져올 수 있음
    public String index(Model model, @LoginUser SessionUser user, @PageableDefault Pageable pageable
            , @RequestParam(value="keyword", required = false) String keyword) {

        Page<Board> boardList;

        if(keyword == null || keyword == "") {
            boardList = boardService.getBoardList(pageable);
            model.addAttribute("keyword", "");
        }
        else {
            boardList = boardService.searchBoard(pageable, keyword);
            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("boardList", boardList);

        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }
        return "board/index";
    }


    @GetMapping("/board/save")
    public String boardSave(Model model, @LoginUser SessionUser user) {
        Board board = new Board();
        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }
        model.addAttribute("board", board);
        return "board/board-save";
    }

    @GetMapping("/board/{id}")
    public String boardView(@PathVariable Long id, Model model, @LoginUser SessionUser user) {

        Board board = boardService.findById(id);

        model.addAttribute("board", board);
        model.addAttribute("commentList", commentService.findAllByBoardId(id));

        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }

        return "board/board-view";
    }

    @GetMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {

        Board board = boardService.findById(id);
        model.addAttribute("board", board);

        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }

        return "board/board-update";
    }
}
