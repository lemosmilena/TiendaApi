package com.example.tiendaapi.security;


import com.example.tiendaapi.security.UserPermission;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.tiendaapi.security.UserPermission.*;



public enum UserRole {
    ADMIN(Set.of(USER_WRITE, USER_READ, PRODUCTO_WRITE, PRODUCTO_READ)),
    CLIENTE(Set.of(USER_WRITE, USER_READ, PRODUCTO_READ));

    private final Set<UserPermission> permissions;


    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }


    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public String getRole(){
        return this.name();
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permisos = getPermissions().stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
                .collect(Collectors.toSet());

        permisos.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permisos;
    }
}
