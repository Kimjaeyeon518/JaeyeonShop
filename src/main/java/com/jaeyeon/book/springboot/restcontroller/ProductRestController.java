package com.jaeyeon.book.springboot.restcontroller;

import com.jaeyeon.book.springboot.domain.UploadFile;
import com.jaeyeon.book.springboot.dto.ProductDto.ProductRequestDto;
import com.jaeyeon.book.springboot.service.ProductService;
import com.jaeyeon.book.springboot.util.S3Uploader;
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
    private S3Uploader s3Uploader;
    String imgPath;

    @PostMapping("/product")
    public Long save(@RequestBody ProductRequestDto productRequestDto) {
        productRequestDto.setProductImg(imgPath);
        return productService.save(productRequestDto);
    }

    @PostMapping("/product/image")
    public ResponseEntity<?> uploadProductImage(@RequestParam("file") MultipartFile file) throws IOException {
//        try {
//            UploadFile uploadedFile = productService.uploadProductImage(file);
//            return ResponseEntity.ok().body("product-upload-image/" + uploadedFile.getSaveFileName());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }

        imgPath = s3Uploader.upload(file, "static");
        return ResponseEntity.ok().body("upload success");

    }


    @PutMapping("/product/{id}")
    public Long update(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) throws IOException {

        return productService.updateProduct(id, productRequestDto);
    }


    @DeleteMapping("/product/{id}")
    public Long delete(@PathVariable Long id) {
        productService.delete(id);
        return id;
    }

//    @GetMapping("/product/cart/{productId}/{userId}")
//    public Long addCart(@PathVariable Long productId, @PathVariable Long userId) {
//        return productService.addCart(productId, userId);
//    }
//    @GetMapping("/product/{id}")
//    public ProductResponseDto findById(@PathVariable Long id) {
//        return productService.findById(id);
//    }
}
