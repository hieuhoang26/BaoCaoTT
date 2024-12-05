package org.example.test.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttributeTypeDTO {
    private Integer id;
    private String name;
    private List<AttributeValueDTO> attributeValues;
}
