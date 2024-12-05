package org.example.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item", schema = "shoptest", catalog = "")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "discount")
    private Double discount;
    @Basic
    @Column(name = "quantity")
    private Short quantity;
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id", nullable = true)
    private Product productByProductId;
    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "id", nullable = true)
    private Order orderByOrderId;


}
