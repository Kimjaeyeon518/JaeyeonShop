package com.jaeyeon.book.springboot.restcontroller;

import com.jaeyeon.book.springboot.dto.CartRequestDto;
import com.jaeyeon.book.springboot.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartRestController {

    private final CartService cartService;

    @PostMapping("/product/cart")
    public Long addCart(@RequestBody CartRequestDto cartRequestDto) {
        return cartService.addCart(cartRequestDto);
    }

    @DeleteMapping("/cart/{cartId}")
    public Long delete(@PathVariable Long cartId) {
        cartService.delete(cartId);
        return cartId;
    }
}
