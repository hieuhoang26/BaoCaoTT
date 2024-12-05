package org.example.test.service;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.CategoryDto;
import org.example.test.model.Category;
import org.example.test.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;
    public Category getById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepository.findAllParentCategory();
        List<CategoryDto> rs = categories.stream().map(category -> {
            return CategoryDto.builder()
                    .id(category.getId())
                    .name(category.getTitle())
                    .parentId(category.getParentId())
                    .build();
        }).toList();
        rs.forEach(category -> {
            category.setSubcategories(
                    retrieveCategoriesByParentId(Math.toIntExact(category.getId()))
            );
        });
        return rs;
    }
    public List<CategoryDto> retrieveCategoriesByParentId(Integer parentId) {
        List<Category> categories =  categoryRepository.findCategoriesByParentId(parentId);
        return categories.stream().map(category -> CategoryDto.builder()
                .id(category.getId())
                .parentId(category.getParentId())
                .name(category.getTitle())
                .build()).toList();
    }
}
