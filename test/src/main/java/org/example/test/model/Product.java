package org.example.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "Product", schema = "shoptest", catalog = "")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "inventory")
    private Short inventory;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "discount", nullable = true)
    private Double discount;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "createdAt")
    private LocalDate createdAt;

    @OneToMany(mappedBy = "productByProductId")
    private Collection<HdnDetail> hdnDetailsById;

    @OneToMany(mappedBy = "productByProductId")
    private Collection<OrderItem> orderItemsById;

//    1-n Product detail
    @OneToMany(mappedBy = "productByProductId")
    private Collection<ProductDetail> productDetailsById;

    @OneToMany(mappedBy = "productByProductId")
    private Collection<ProductReview> productReviewsById;

    //    n-n category
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    Collection<Category> categories;


}
