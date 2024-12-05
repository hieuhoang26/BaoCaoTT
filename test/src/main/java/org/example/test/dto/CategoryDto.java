package org.example.test.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    long id;
    String name;
    long parentId;
    List<CategoryDto> subcategories;
}