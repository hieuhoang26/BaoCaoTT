package org.example.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "product_review", schema = "shoptest", catalog = "")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReview {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "rating")
    private Short rating;
    @Basic
    @Column(name = "published")
    private Byte published;
    @Basic
    @Column(name = "createdAt")
    private LocalDate createdAt;
    @Basic
    @Column(name = "publishedAt")
    private LocalDate publishedAt;
    @Basic
    @Column(name = "content")
    private String content;
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id", nullable = false)
    private Product productByProductId;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User userByUserId;
    @ManyToOne
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    private ProductReview productReviewByParentId;
    @OneToMany(mappedBy = "productReviewByParentId")
    private Collection<ProductReview> productReviewsById;


}
