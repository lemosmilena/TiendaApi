package com.example.tiendaapi.security.auth;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "authorities")
public class MiSimpleGrantedAuthority implements GrantedAuthority {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer authority_id;
    @Column(nullable = false)
    private String role;
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY) // no necesito saber mas que su rol, por eso es lazy
    private Set<UserEntity> usuarios;

    public MiSimpleGrantedAuthority() {
    }

    public MiSimpleGrantedAuthority(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
