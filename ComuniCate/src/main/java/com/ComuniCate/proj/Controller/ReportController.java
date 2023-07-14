package com.ComuniCate.proj.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ComuniCate.proj.Entity.Photo;
import com.ComuniCate.proj.Entity.Report;
import com.ComuniCate.proj.Model.ReportDTO;
import com.ComuniCate.proj.Service.PhotoService;
import com.ComuniCate.proj.Service.ReportService;

//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/report")
public class ReportController {

	@Autowired
	private ReportService rs;

	@Autowired
	private PhotoService ps;
	
//	@PostMapping
//	public ResponseEntity<?> save(@ModelAttribute ReportDTO report,
//			@RequestParam("photo") List<MultipartFile> photoFiles) throws IOException {
//		return ResponseEntity.ok(rs.add(report, photoFiles));
//	}

	@PostMapping
	public ResponseEntity<?> save(@ModelAttribute ReportDTO report,
			@RequestParam("photo") List<MultipartFile> photoFiles) throws IOException {
		return ResponseEntity.ok(rs.add(report, photoFiles));
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<?> update(@PathVariable long id, @RequestBody Report report) {
//		return ResponseEntity.ok(rs.update(report, id));
//	}
	
	 //@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}/{userId}")
	public ResponseEntity<?> update(@PathVariable long id, @PathVariable long userId, @ModelAttribute ReportDTO report,
			@RequestParam("photo") List<MultipartFile> photoFiles) throws IOException {
		return ResponseEntity.ok(rs.update(report, id, userId, photoFiles));
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		return ResponseEntity.ok(rs.delete(id));
	}

	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable long id) {
		return ResponseEntity.ok(rs.getById(id));
	}

	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(rs.getAll());
	}

	@GetMapping("/user/{username}")
	public ResponseEntity<?> getAllByUser(@PathVariable String username) {
		return ResponseEntity.ok(rs.findAllByUserName(username));
	}

}
