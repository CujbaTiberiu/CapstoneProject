package com.ComuniCate.proj.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ComuniCate.proj.Entity.Role;
import com.ComuniCate.proj.Enum.ERole;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRoleName(ERole roleName);

}
