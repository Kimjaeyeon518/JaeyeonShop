package com.jaeyeon.book.springboot.service;

import com.jaeyeon.book.springboot.domain.Board;
import com.jaeyeon.book.springboot.domain.Product;
import com.jaeyeon.book.springboot.domain.enums.ProductStatus;
import com.jaeyeon.book.springboot.dto.ProductDto.ProductRequestDto;
import com.jaeyeon.book.springboot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long save(ProductRequestDto productRequestDto) {

        Product product = new Product();

        product.setName(productRequestDto.getName());
        product.setCategory(productRequestDto.getCategory());
        product.setDescription(productRequestDto.getDescription());
        product.setDiscount(productRequestDto.getDiscount());
        product.setPrice(productRequestDto.getPrice());
        product.setProductStatus(ProductStatus.SALE);

        return productRepository.save(product).getId();
    }

//    @Transactional
//    public String updateProduct(Long id, ProductRequestDto.UpdateRequestDto updateRequestDto) {
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));
//
//        product.setName(updateRequestDto.getName());
//        product.setPrice(updateRequestDto.getPrice());
//        product.setCategory(updateRequestDto.getCategory());
//        product.setDescription(updateRequestDto.getDescription());
//        product.setDiscount(updateRequestDto.getDiscount());
//
//        productRepository.save(product);
//
//        return "상품 정보 수정이 완료되었습니다.";
//    }

    @Transactional
    public void delete (Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));

        productRepository.delete(product);
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));

        return product;
    }

    @Transactional
    public Page<Product> getProductList(Pageable pageable, String category) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "id")); // <- Sort 추가

        return productRepository.findAllByCategory(pageable, category);
    }


    @Transactional
    public Page<Product> searchPosts(Pageable pageable, String keyword) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "id")); // <- Sort 추가

        return productRepository.findAllByNameContaining(pageable, keyword);
    }
}
