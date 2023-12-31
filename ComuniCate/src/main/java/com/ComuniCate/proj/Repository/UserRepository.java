package com.ComuniCate.proj.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ComuniCate.proj.Entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);
    
    Optional<User> findByTaxCode(String taxcode);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    Boolean existsByTaxCode(String email);
}
