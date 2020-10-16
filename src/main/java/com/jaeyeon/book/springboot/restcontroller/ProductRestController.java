package com.jaeyeon.book.springboot.restcontroller;

import com.jaeyeon.book.springboot.domain.UploadFile;
import com.jaeyeon.book.springboot.dto.ProductDto.ProductRequestDto;
import com.jaeyeon.book.springboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.jaeyeon.book.springboot.util.UploadFileUtil.PRODUCT_UPLOAD_IMAGE;

@RequiredArgsConstructor
@RestController
public class ProductRestController {

    private final ProductService productService;

    @PostMapping("/product")
    public Long save(@RequestBody ProductRequestDto productRequestDto) {
        return productService.save(productRequestDto);
    }

    @PostMapping("/product/image")
    public ResponseEntity<?> uploadProductImage(@RequestParam("file") MultipartFile file) throws IOException {

        try {
            UploadFile uploadedFile = productService.uploadProductImage(file);
            return ResponseEntity.ok().body("product-upload-image/" + uploadedFile.getSaveFileName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
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
