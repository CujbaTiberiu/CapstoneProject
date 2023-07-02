package com.ComuniCate.proj.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ComuniCate.proj.Entity.User;
import com.ComuniCate.proj.Service.UserService;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService us;

	@PostMapping
	public ResponseEntity<?> save(@RequestBody User user) throws Exception {
		return ResponseEntity.ok(us.add(user));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody User user) {
		return ResponseEntity.ok(us.update(user, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		return ResponseEntity.ok(us.delete(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable long id) {
		return ResponseEntity.ok(us.getById(id));
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(us.getAll());
	}

}
