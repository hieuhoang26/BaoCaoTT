package org.example.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "attribute_value", schema = "shoptest", catalog = "")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttributeValue {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "attribute_type_id", referencedColumnName = "id")
    private AttributeType attributeTypeByAttributeTypeId;

//    1-n product detail
    @OneToMany(mappedBy = "attributeValueByAttributeValId")
    private Collection<ProductDetail> productDetailsById;


}
