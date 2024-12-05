package org.example.test.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
public class CreateProduct {
    private String title;
    private Short inventory;
    private Double price;
    private Double discount;
    private MultipartFile image;

    private List<String> category;

    private List<TypeValueDto> detail;





}

