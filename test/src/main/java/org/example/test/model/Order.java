package org.example.test.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.test.util.OrderStatus;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "OrderProduct", schema = "shoptest", catalog = "")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Basic
    @Column(name = "subTotal")
    private Double subTotal;  //The total price of the Order Items.
    @Basic
    @Column(name = "shipping")
    private Double shipping; // phi van chuyen
    @Basic
    @Column(name = "total")
    private Double total;  // The total price of the Order including tax and shipping
    @Basic
    @Column(name = "discount")
    private Double discount;  // The total discount of the Order Items
    @Basic
    @Column(name = "grandTotal")
    private Double grandTotal; //The grand total of the order to be paid by the buyer.
    @Basic
    @Column(name = "mobile")
    private String mobile;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "createdAt")
    private LocalDate createdAt;
    @Basic
    @Column(name = "updatedAt")
    private LocalDate updatedAt;
    @Basic
    @Column(name = "content")
    private String content;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userByUserId;

    @OneToMany(mappedBy = "orderByOrderId")
    private Collection<OrderItem> orderItemsById;



}
