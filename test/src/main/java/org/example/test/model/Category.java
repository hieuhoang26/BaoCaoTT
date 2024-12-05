package org.example.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "category", schema = "shoptest", catalog = "")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "parentId")
    private Long parentId;
    @Basic
    @Column(name = "title")
    private String title;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    Collection<Product> products;

}
