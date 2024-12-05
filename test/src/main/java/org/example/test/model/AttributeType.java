package org.example.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "attribute_type", schema = "shoptest", catalog = "")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttributeType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "attributeTypeByAttributeTypeId")
    private Collection<AttributeValue> attributeValuesById;




}
