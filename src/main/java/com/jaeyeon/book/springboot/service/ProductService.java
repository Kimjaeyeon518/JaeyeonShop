package com.jaeyeon.book.springboot.service;

import com.jaeyeon.book.springboot.domain.Cart;
import com.jaeyeon.book.springboot.domain.Product;
import com.jaeyeon.book.springboot.domain.User;
import com.jaeyeon.book.springboot.domain.enums.ProductStatus;
import com.jaeyeon.book.springboot.dto.ProductDto.ProductRequestDto;
import com.jaeyeon.book.springboot.repository.CartRepository;
import com.jaeyeon.book.springboot.repository.ProductRepository;
import com.jaeyeon.book.springboot.repository.UserRepository;
import com.jaeyeon.book.springboot.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.jaeyeon.book.springboot.domain.UploadFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.jaeyeon.book.springboot.util.FileUploadProperties;

@RequiredArgsConstructor
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final Path rootLocation;

    private String productImgName = "";

    @Autowired
    public ProductService(FileUploadProperties prop) {
        this.rootLocation = Paths.get(prop.getProductUploadDir())
                .toAbsolutePath().normalize();
    }

    @Transactional
    public Long save(ProductRequestDto productRequestDto) {

        Product product = new Product();

        product.setName(productRequestDto.getName());
        product.setCategory(productRequestDto.getCategory());
        product.setDescription(productRequestDto.getDescription());
        product.setDiscount(productRequestDto.getDiscount());
        product.setPrice(productRequestDto.getPrice());
        product.setProductImg(productImgName);
        product.setProductStatus(ProductStatus.SALE);

        return productRepository.save(product).getId();
    }


    @Transactional
    public Long updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));

        product.setName(productRequestDto.getName());
        product.setCategory(productRequestDto.getCategory());
        product.setDescription(productRequestDto.getDescription());
        product.setDiscount(productRequestDto.getDiscount());
        product.setPrice(productRequestDto.getPrice());
        product.setProductImg(productImgName);
        product.setProductStatus(ProductStatus.SALE);

        return productRepository.save(product).getId();
    }

    @Transactional
    public UploadFile uploadProductImage(MultipartFile file) throws Exception {
        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }

            String saveFileName = UploadFileUtil.fileSave(rootLocation.toString(), file);

            if (saveFileName.toCharArray()[0] == '/') {
                saveFileName = saveFileName.substring(1);
            }

            Resource resource = loadAsResource(saveFileName);
            productImgName = saveFileName;
            UploadFile saveFile = new UploadFile();
            saveFile.setSaveFileName(saveFileName);
            saveFile.setFileName(file.getOriginalFilename());
            saveFile.setContentType(file.getContentType());
            saveFile.setFilePath(rootLocation.toString().replace(File.separatorChar, '/') + File.separator + saveFileName);
            saveFile.setSize(resource.contentLength());

            return saveFile;
        } catch (IOException e) {
            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    private Resource loadAsResource(String fileName) throws Exception {
        try {
            if (fileName.toCharArray()[0] == '/') {
                fileName = fileName.substring(1);
            }

            Path file = loadPath(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new Exception("Could not read file: " + fileName);
            }
        } catch (Exception e) {
            throw new Exception("Could not read file: " + fileName);
        }
    }

    private Path loadPath(String fileName) {
        return rootLocation.resolve(fileName);
    }

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
    public Page<Product> searchProduct(Pageable pageable, String keyword) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "id")); // <- Sort 추가

        return productRepository.findAllByNameContaining(pageable, keyword);
    }
}
