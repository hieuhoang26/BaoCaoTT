package org.example.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_detail", schema = "shoptest", catalog = "")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

//    n-1 product
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product productByProductId;

//    n-1 value
    @ManyToOne
    @JoinColumn(name = "attribute_val_id", referencedColumnName = "id")
    private AttributeValue attributeValueByAttributeValId;


}
