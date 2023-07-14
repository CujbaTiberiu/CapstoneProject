package com.ComuniCate.proj.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ComuniCate.proj.Entity.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{

	Optional<Photo> findByName(String fileName);
}
