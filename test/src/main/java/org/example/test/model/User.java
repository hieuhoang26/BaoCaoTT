package org.example.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "User", schema = "shoptest", catalog = "")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "mobile")
    private String mobile;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "avatar")
    private String avatar;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<Order> ordersById;

    @OneToMany(mappedBy = "userByUserId")
    private Collection<ProductReview> productReviewsById;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "RoleID", referencedColumnName = "ID_Role")
    private Role roleByRoleId;

}
