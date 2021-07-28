package com.example.tiendaapi.security.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findUserEntityByUsername(String user_name);
}
