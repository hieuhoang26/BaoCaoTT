package org.example.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "Hdn", schema = "shoptest", catalog = "")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hdn {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "supplier")
    private String supplier;
    @Basic
    @Column(name = "createdAt")
    private LocalDate createdAt;
    @Basic
    @Column(name = "totalAmount")
    private Double totalAmount;
    @Basic
    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy = "hdnByPurchaseInvoiceId")
    private Collection<HdnDetail> hdnDetailsById;

}
