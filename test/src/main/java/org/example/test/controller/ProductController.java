package org.example.test.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.test.dto.CreateProduct;
import org.example.test.dto.ProductDto;
import org.example.test.model.Product;
import org.example.test.service.ProductService;
import org.example.test.util.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping(value = Uri.getAllProduct)
    public ResponseEntity getAll() {
        try {
            List<ProductDto> rs = productService.getAll();
            return new ResponseEntity<>(rs, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while fetching all products", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = Uri.product)
    public ResponseEntity getProduct(@RequestParam Integer id) {
        try {
//            Map<String,String> rs = productService.getDetail(id);
            ProductDto rs = productService.getProduct(id);
            return new ResponseEntity<>(rs,HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while fetching all products", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = Uri.product)
    public ResponseEntity createProduct(@ModelAttribute CreateProduct request) {
        try {
            String id = productService.create(request);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while fetching all products", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}
