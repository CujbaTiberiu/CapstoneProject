package com.ComuniCate.proj.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ComuniCate.proj.Entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>{
	
	public List<Report> findByUserId(long id);

}
