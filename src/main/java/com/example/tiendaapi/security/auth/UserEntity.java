package com.example.tiendaapi.security.auth;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "usuarios")

public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String user_name;
    private String password;
    // @Basic este es un tipo de dato primitivo
    // @ElementCollection (fetch = FetchType.EAGER)// lo que tenes aca es una coleccion de elementos
    // @Convert () nos permite guardar una cosa y leer otra, se pasa la clase inicial y la que queremos guardar
    @JsonBackReference
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER) // porque necesito saber que rol tiene
    @JoinTable(
            name ="users_x_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =  @JoinColumn(name = "authority_id")
    )
    private Collection <MiSimpleGrantedAuthority> authorities; // es una interfaz que me permite poner cualquier cosa, mapa, arraylist, set, lista, set.
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean enabled;
    private boolean credentialsNonExpired;

    public UserEntity() {
    }

    public UserEntity(Integer user_id, String user_name, String password, Collection<MiSimpleGrantedAuthority> authorities, boolean accountNonExpired, boolean accountNonLocked, boolean enabled, boolean credentialsNonExpired) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.enabled = enabled;
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<MiSimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", enabled=" + enabled +
                ", credentialsNonExpired=" + credentialsNonExpired +
                '}';
    }
}
