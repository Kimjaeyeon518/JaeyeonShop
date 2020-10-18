package com.jaeyeon.book.springboot.controller;

import com.jaeyeon.book.springboot.config.auth.LoginUser;
import com.jaeyeon.book.springboot.config.auth.dto.SessionUser;
import com.jaeyeon.book.springboot.domain.Product;
import com.jaeyeon.book.springboot.service.ProductService;
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
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/productList")        // 이제 어느 컨트롤러든지 @LoginUser만 사용하면 세션정보를 가져올 수 있음
    public String index(Model model, @LoginUser SessionUser user, @PageableDefault Pageable pageable
            , @RequestParam(value="category", required = false) String category) {      // postsService.findAllDesc()로 가져온 결과를 "posts"로 index.mustache에 전달

        Page<Product> productList;

        productList = productService.getProductList(pageable, category);
        model.addAttribute("productList", productList);
        model.addAttribute("category", category);

        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }

        return "product/productList";
    }

    @GetMapping("/product/{id}")
    public String productView(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("category", product.getCategory());

        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }

        return "product/product-view";
    }

    @GetMapping("/product/update/{id}")
    public String productUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        Product product = productService.findById(id);

        model.addAttribute("product", product);

        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }

        return "product/product-update";
    }

    @GetMapping(value = "/selectCategory")
    public String openSelectCategory(@LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }
        return "product/selectCategory";
    }

    @GetMapping(value = "/addProduct")
    public String openProductInsert(@RequestParam(value="category", required = false) String category, @LoginUser SessionUser user, Model model) {
        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }
        model.addAttribute("category", category);
        return "product/product-save";
    }

    @GetMapping(value = "/product/buy/{productId}")
    public String buyProduct(@LoginUser SessionUser user, Model model, @PathVariable Long productId) {
        if (user != null) {
            model.addAttribute("user", userService.findById(user.getId()));
        }
        model.addAttribute("product", productService.findById(productId));
        return "product/product-buy";
    }
}
