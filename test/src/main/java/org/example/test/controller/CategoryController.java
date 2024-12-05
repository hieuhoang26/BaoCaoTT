package org.example.test.controller;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.CategoryDto;
import org.example.test.repository.CategoryRepository;
import org.example.test.service.CategoryService;
import org.example.test.util.Uri;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoryController {
    final CategoryService categoryService;

    @GetMapping(value = Uri.category)
    public ResponseEntity GetAllCategory(){
        List<CategoryDto> rs = categoryService.getAll();
        return new ResponseEntity(rs, HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }
}
