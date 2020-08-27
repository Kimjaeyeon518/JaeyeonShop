package com.jaeyeon.book.springboot.web;

import com.jaeyeon.book.springboot.config.auth.LoginUser;
import com.jaeyeon.book.springboot.config.auth.dto.SessionUser;
import com.jaeyeon.book.springboot.service.PostsService;
import com.jaeyeon.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")        // 이제 어느 컨트롤러든지 @LoginUser만 사용하면 세션정보를 가져올 수 있음
    public String index(Model model, @LoginUser SessionUser user) {      // postsService.findAllDesc()로 가져온 결과를 "posts"로 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
