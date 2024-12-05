package org.example.test.service;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.CreateProduct;
import org.example.test.dto.ProductDto;
import org.example.test.dto.TypeValueDto;
import org.example.test.model.*;
import org.example.test.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
    final ProductDetailRepository productDetailRepository;
    final AttributeTypeRepository attributeTypeRepository;
    final AttributeValueRepository attributeValueRepository;
    final CategoryService categoryService;

    public List<ProductDto> getAll() {
        java.util.List<Product> list = productRepository.findAll();
        return list.stream().map(product -> {
            return ProductDto.builder()
                    .id(product.getId())
                    .title(product.getTitle())
                    .inventory(product.getInventory())
                    .price(product.getPrice())
                    .discount(product.getDiscount())
                    .image(product.getImage())
                    .createdAt(product.getCreatedAt())
                    .build();
        }).collect(Collectors.toList());
    }
    public ProductDto getProduct(Integer id){
        Product product = productRepository.findById(id).orElse(null);
        assert product != null;
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .inventory(product.getInventory())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .build();
    }
    public Map<String, String> getDetail(Integer id) {
        Map<String, String> productInfo = new HashMap<>();
        List<ProductDetail> details = productDetailRepository.findByProductId(id);
        for (ProductDetail pd : details) {
            AttributeValue attributeValue = pd.getAttributeValueByAttributeValId();
            if (attributeValue != null) {
                String attributeName = attributeValue.getAttributeTypeByAttributeTypeId().getName();
                String value = attributeValue.getName();
                productInfo.put(attributeName, value);
            }


        }
        return productInfo;
    }

    public String create(CreateProduct rq) {
        Product product = new Product();
        if (rq.getTitle() != null) {
            product.setTitle(rq.getTitle());
        }
        if (rq.getPrice() != null) {
            product.setPrice(rq.getPrice());
        }
        if (rq.getDiscount() != null) {
            product.setDiscount(rq.getDiscount());
        }
        if (rq.getInventory() != null) {
            product.setInventory(rq.getInventory());
        }
        String imageDir = "D:/DTPM/Fe/shoptest/public/images/";

        if ((rq.getImage() != null && !rq.getImage().isEmpty())) {
            MultipartFile image = rq.getImage();
            String fileName = image.getOriginalFilename();
            if (!fileName.endsWith(".jpg") && !fileName.endsWith(".png")) {
                fileName += ".jpg";
            }
            String imagePath = imageDir + fileName;
            try {
                image.transferTo(new File(imagePath));
                product.setImage(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        product.setCreatedAt(LocalDate.now());


        productRepository.save(product);
        if (rq.getCategory() != null) {
            List<Category> categories = rq.getCategory().stream()
                    .map(id -> categoryService.getById(Integer.valueOf(id)))
                    .filter(Objects::nonNull)
                    .toList();

            product.setCategories(new HashSet<>(categories));
        }


        if (rq.getDetail() != null) {
            for (TypeValueDto entry : rq.getDetail()) {
                Integer attributeTypeName = entry.getTypeId();
                Integer attributeValueId = entry.getValueId();

                AttributeType attributeType = attributeTypeRepository
                        .findById(attributeTypeName)
                        .orElseThrow(() -> new RuntimeException("type not found"));

                AttributeValue attributeValue = attributeValueRepository
                        .findById(attributeValueId)
                        .orElseThrow(() -> new RuntimeException("value not found"));

                ProductDetail productDetail = new ProductDetail();
                productDetail.setProductByProductId(product);
                productDetail.setAttributeValueByAttributeValId(attributeValue);

                productDetailRepository.save(productDetail);
            }
        }
        productRepository.save(product);


        return product.getId().toString();
    }
}

