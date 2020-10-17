package com.jaeyeon.book.springboot.controller;

import com.jaeyeon.book.springboot.config.auth.LoginUser;
import com.jaeyeon.book.springboot.config.auth.dto.SessionUser;
import com.jaeyeon.book.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final UserService userService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("template", "fragments/content/main");

        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }

        return "index";
    }

    @GetMapping(value = "/member/register")
    public String openMembersignUp(@LoginUser SessionUser user) {		//  Model 인터페이스는 데이터를 뷰로 전달하는 데 사용
        // BoardDTO 타입의 '비어있는' 객체를 만들어 게시글 '등록' 기능 사용 준비

        return "member/register";
    }

    @GetMapping(value = "/member/login")
    public String openMembersignIn(@LoginUser SessionUser user) {
        return "member/login";
    }

}
