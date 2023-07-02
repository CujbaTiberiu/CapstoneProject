package com.buildweek.epicode.energy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buildweek.epicode.energy.entity.ERole;
import com.buildweek.epicode.energy.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
