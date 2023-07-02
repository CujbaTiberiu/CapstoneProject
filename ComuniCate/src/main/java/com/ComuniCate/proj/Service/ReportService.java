package com.ComuniCate.proj.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ComuniCate.proj.Entity.Photo;
import com.ComuniCate.proj.Entity.Report;
import com.ComuniCate.proj.Entity.User;
import com.ComuniCate.proj.Model.ReportDTO;
import com.ComuniCate.proj.Repository.ReportRepository;
import com.ComuniCate.proj.Repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ReportService {

	@Autowired
	ReportRepository db;
	@Autowired
	UserRepository dbUser;
	@Autowired
	private PhotoService dbPhoto;
	
	@Transactional
	public Report add(ReportDTO r, List<MultipartFile> photoFiles) throws IOException {
		if (!dbUser.existsByUsername(r.getUsername())) {
			throw new EntityNotFoundException("Username doesn't exist in Database!");
		}
		//Reecupera l'oggeto User dal database grazie allo Username
		User user = dbUser.findByUsername(r.getUsername()).get();

		Report rep = new Report();
		rep.setDescription(r.getDescription());
		rep.setLatitude(r.getLatitude());
		rep.setLongitude(r.getLongitude());
		rep.setReportType(r.getReportType());
		rep.setUser(user);

		List<Photo> photos = new ArrayList<>();

		if (photoFiles != null && !photoFiles.isEmpty()) {
			for (MultipartFile photoFile : photoFiles) {
				byte[] imageData = photoFile.getBytes();
				String fileName = photoFile.getOriginalFilename();

				// Recupera l'oggetto Photo dal database invece di crearne uno nuovo
				Photo photo = dbPhoto.findByFileName(fileName);
				if (photo == null) {
					// Se l'oggetto Photo non esiste, utilizza uploadImage per salvarlo nel database
					photo = dbPhoto.uploadImage(photoFile);
				}

				// Aggiungi l'oggetto Photo alla lista delle foto del Report
				photos.add(photo);
			}
		}

		rep.setPhotos(photos);

		for (Photo photo : photos) {
			photo.setReport(rep);
		}

		return db.save(rep);
	}

	public Report update(Report r, long id) {
		if (!db.existsById(id)) {
			throw new EntityNotFoundException("Report doesn't exist in database!");
		}
		return db.save(r);
	}

	public String delete(long id) {
		if (!db.existsById(id)) {
			throw new EntityNotFoundException("Report doesn't exist in database!");
		}
		db.deleteById(id);
		return "Report removed successfully!";
	}

	public Report getById(long id) {
		if (!db.existsById(id)) {
			throw new EntityNotFoundException("Report doesn't exist in database!");
		}
		return db.findById(id).get();
	}

	public List<Report> getAll() {
		return db.findAll();
	}

	public List<Report> findByUserId(long id) {
		if (dbUser.findById(id).get().getReport() == null) {
			throw new EntityNotFoundException("There are no reports in database!");
		}
		return dbUser.findById(id).get().getReport();
	}

}