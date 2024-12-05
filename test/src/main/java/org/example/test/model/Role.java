package org.example.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Role")
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Role", nullable = false, length = 10)
    private Integer id;
    @Basic
    @Column(name = "Role", nullable = true)
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "roleByRoleId")
    private Collection<User> UsersByIdRole;
}
