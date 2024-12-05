package org.example.test.service;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.AttributeTypeDTO;
import org.example.test.dto.AttributeValueDTO;
import org.example.test.model.AttributeType;
import org.example.test.repository.AttributeTypeRepository;
import org.example.test.repository.AttributeValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttributeTypeService {
    final AttributeTypeRepository attributeTypeRepository;
    final AttributeValueRepository attributeValueRepository;

    public List<AttributeTypeDTO> getAllAttributeTypes() {
        List<AttributeType> attributeTypes = attributeTypeRepository.findAll();

        List<AttributeTypeDTO> attributeTypeDTOs = attributeTypes.stream().map(attributeType -> {
            AttributeTypeDTO dto = new AttributeTypeDTO();
            dto.setId(attributeType.getId());
            dto.setName(attributeType.getName());

            // Map AttributeValue to AttributeValueDTO
            List<AttributeValueDTO> attributeValueDTOs = attributeType.getAttributeValuesById().stream().map(attributeValue -> {
                AttributeValueDTO valueDTO = new AttributeValueDTO();
                valueDTO.setId(attributeValue.getId());
                valueDTO.setName(attributeValue.getName());
                return valueDTO;
            }).collect(Collectors.toList());

            dto.setAttributeValues(attributeValueDTOs);
            return dto;
        }).collect(Collectors.toList());

        return attributeTypeDTOs;
    }
}
