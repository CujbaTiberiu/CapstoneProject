package com.ComuniCate.proj.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ComuniCate.proj.Entity.Photo;
import com.ComuniCate.proj.Model.ImageUtils;
import com.ComuniCate.proj.Repository.PhotoRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PhotoService {

	@Autowired PhotoRepository db;
	
	//@Transactional
	public Photo uploadImage(MultipartFile file) throws IOException {
		byte[] imageData = ImageUtils.compressImage(file.getBytes());
	    return db.save(Photo.builder()
	            .name(file.getOriginalFilename())
	            .type(file.getContentType())
	            .imageData(imageData)
	            .build());
    }


	@Transactional
    public byte[] downloadImage(String fileName) {
        Optional<Photo> dbImageData = db.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
	
    public Photo findByFileName(String fileName) {
        return db.findByName(fileName).orElse(null);
    }

	
	public Photo add(Photo p) {
		return db.save(p);
	}
	
	/*public Photo update(Photo p, long id) {
		if(!db.existsById(id)) {
			throw new EntityExistsException("Photo doesn't exist in database!");
		}
		return db.save(p);
	}
	
	public String delete(long id) {
		if(!db.existsById(id)) {
			throw new EntityExistsException("Photo doesn't exist in database!");
		}
		 db.deleteById(id);
		 return "Photo deleted successfully!";
	}
	
	public Photo getById(long id) {
		if(!db.existsById(id)){
			throw new EntityNotFoundException("Photo doesn't exist in database!");
		}
		return db.findById(id).get();
	}
	
	public List<Photo> getAll(){
		return db.findAll();
	}*/
}
