package com.jaeyeon.book.springboot.service;

import com.jaeyeon.book.springboot.domain.*;
import com.jaeyeon.book.springboot.dto.CartRequestDto;
import com.jaeyeon.book.springboot.repository.CartRepository;
import com.jaeyeon.book.springboot.repository.ProductRepository;
import com.jaeyeon.book.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Transactional
    public Long addCart(CartRequestDto cartRequestDto) {

        Optional<User> user = userRepository.findById(cartRequestDto.getUserId());
        Optional<Product> product = productRepository.findById(cartRequestDto.getProductId());

        Cart cart = new Cart();

        cart.setUser(user.get());
        cart.setProduct(product.get());

        return cartRepository.save(cart).getId();
    }

    @Transactional(readOnly = true)
    public List<Cart> findAllByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId).stream()
                .map(Cart::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));

        cartRepository.delete(cart);
    }

}
