package org.example.test.dto;



import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;

    private String title;

    private Short inventory;

    private Double price;

    private Double discount;

    private String image;

    private LocalDate createdAt;
}
