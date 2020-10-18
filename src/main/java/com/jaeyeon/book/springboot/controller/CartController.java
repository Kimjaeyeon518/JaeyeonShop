package com.jaeyeon.book.springboot.controller;

import com.jaeyeon.book.springboot.config.auth.LoginUser;
import com.jaeyeon.book.springboot.config.auth.dto.SessionUser;
import com.jaeyeon.book.springboot.domain.Board;
import com.jaeyeon.book.springboot.domain.Cart;
import com.jaeyeon.book.springboot.domain.User;
import com.jaeyeon.book.springboot.service.CartService;
import com.jaeyeon.book.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CartController {

    private final UserService userService;
    private final CartService cartService;

    @GetMapping("/cartList")
    public String cartList(Model model, @LoginUser SessionUser user) {

        List<Cart> cartList = cartService.findAllByUserId(user.getId());

        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }

        model.addAttribute("cartList", cartList);

        return "cart/cartList";
    }
}
