package com.awsstudies.estudos.model.entities;

import com.awsstudies.estudos.enums.UserRoles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -7615956224876638858L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRoles userRoles;
    private String urlImage;

    public User(String name, String email, String password, UserRoles userRoles, String urlImage) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userRoles = userRoles;
        this.urlImage = urlImage;
    }
}
