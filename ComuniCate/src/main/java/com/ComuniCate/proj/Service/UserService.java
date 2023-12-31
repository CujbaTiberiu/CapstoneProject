package com.ComuniCate.proj.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ComuniCate.proj.Entity.Role;
import com.ComuniCate.proj.Entity.User;
import com.ComuniCate.proj.Enum.ERole;
import com.ComuniCate.proj.Model.EmailValidator;
import com.ComuniCate.proj.Model.TaxCodeValidator;
import com.ComuniCate.proj.Repository.RoleRepository;
import com.ComuniCate.proj.Repository.UserRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	UserRepository db;
	@Autowired
	AuthServiceImpl auth;
	@Autowired
	RoleRepository roleRepository;

	// add a new user - ADMIN only
	public User add(User u) throws Exception {
		if (db.existsByEmail(u.getEmail())) {
			throw new EntityExistsException("User with this mail already exist!");
		}

		if (!EmailValidator.validateEmail(u.getEmail())) {
			throw new Exception("Invalid email format!");
		}

		if (db.existsByUsername(u.getUsername())) {
			throw new EntityExistsException("User with this username already exist!");
		}

		if (db.existsByTaxCode(u.getTaxCode())) {
			throw new EntityExistsException("User with this taxcode already exist!");
		}

		if (!TaxCodeValidator.validateTaxCode(u.getTaxCode())) {
			throw new Exception("Invalid taxcode format!");
		}

		// encrypting password as in AuthServiceImpl
		u.setPassword(auth.passwordEncoder.encode(u.getPassword()));

		Set<Role> roles = new HashSet<>();
		// sets User role if not specified as in AuthServiceImpl
		if (u.getRoles() == null || u.getRoles().isEmpty()) {
			Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
					.orElseThrow(() -> new Exception("User role not found!"));
			roles.add(userRole);
		}
		u.setRoles(roles);
		return db.save(u);
	}

	public User update(User u, long id) {
		if (!db.existsById(id)) {
			throw new EntityNotFoundException("User doesn't exist in database!");
		}

		User existingUser = db.findById(id).get();

		if (u.getPassword() != null) {
			existingUser.setPassword(auth.passwordEncoder.encode(u.getPassword()));
		}

		if (u.getRoles() != null && !u.getRoles().isEmpty()) {
			existingUser.setRoles(u.getRoles());
		}
		return db.save(existingUser);
	}

	public String delete(long id) {
		if (!db.existsById(id)) {
			throw new EntityNotFoundException("User doesn't exist in database!");
		}
		db.deleteById(id);
		return "User removed successfully!";
	}

	public User getById(long id) {
		if (!db.existsById(id)) {
			throw new EntityNotFoundException("User doesn't exist in database!");
		}
		return db.findById(id).get();
	}

	public List<User> getAll() {
		return db.findAll();
	}
}
