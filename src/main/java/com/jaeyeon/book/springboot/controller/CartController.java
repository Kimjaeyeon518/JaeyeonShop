package com.jaeyeon.book.springboot.controller;

import com.jaeyeon.book.springboot.config.auth.LoginUser;
import com.jaeyeon.book.springboot.config.auth.dto.SessionUser;
import com.jaeyeon.book.springboot.domain.Cart;
import com.jaeyeon.book.springboot.domain.Product;
import com.jaeyeon.book.springboot.service.CartService;
import com.jaeyeon.book.springboot.service.ProductService;
import com.jaeyeon.book.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CartController {

    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("/cartList")
    public String cartList(Model model, @LoginUser SessionUser user) {

        List<Cart> cartList = cartService.findAllByUserId(user.getId());
        int totalPrice = 0;
        int totalDiscountPrice = 0;
        for(Cart cart : cartList) {
            Long productId = cart.getProduct().getId();
            Product product = productService.findById(productId);

            totalPrice += product.getPrice() * cart.getCount();
            totalDiscountPrice += (product.getPrice()-(product.getPrice()*product.getDiscount())/100) * cart.getCount();
        }

        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }

        model.addAttribute("cartList", cartList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalDiscountPrice", totalDiscountPrice);

        return "cart/cartList";
    }

    // 장바구니에서 구매
    @GetMapping(value = "/cart/buy")
    public String buyCartProduct(@LoginUser SessionUser user, Model model) {

        List<Cart> cartList = cartService.findAllByUserId(user.getId());

        int totalPrice = 0;
        int totalDiscountPrice = 0;
        for(Cart cart : cartList) {

            Long productId = cart.getProduct().getId();
            Product product = productService.findById(productId);

            totalPrice += product.getPrice() * cart.getCount();
            totalDiscountPrice += (product.getPrice()-(product.getPrice()*product.getDiscount())/100) * cart.getCount();
        }

        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalDiscountPrice", totalDiscountPrice);
        model.addAttribute("cartList", cartList);

        return "product/product-order";
    }
}
