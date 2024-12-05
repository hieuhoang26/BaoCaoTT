package org.example.test.controller;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.AttributeTypeDTO;
import org.example.test.service.AttributeTypeService;
import org.example.test.util.Uri;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Attribute {
    final AttributeTypeService attributeTypeService;
    @GetMapping(Uri.att)
    public List<AttributeTypeDTO> getAllAttributes() {
        return attributeTypeService.getAllAttributeTypes();
    }

}
