package com.jaeyeon.book.springboot.restcontroller;

import com.jaeyeon.book.springboot.dto.ProductDto.ProductRequestDto;
import com.jaeyeon.book.springboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class ProductRestController {

    private final ProductService productService;

    @PostMapping("/product")
    public Long save(@RequestBody ProductRequestDto productRequestDto) {
        return productService.save(productRequestDto);
    }

//
//    @PutMapping("/product/{id}")
//    public String update(@PathVariable Long id, Product product) throws IOException {
//
//        productService.updateProduct(id, product);
//
//        return "redirect:/product/index";
//    }


    @DeleteMapping("/product/{id}")
    public Long delete(@PathVariable Long id) {
        productService.delete(id);
        return id;
    }

//    @GetMapping("/product/{id}")
//    public ProductResponseDto findById(@PathVariable Long id) {
//        return productService.findById(id);
//    }
}
