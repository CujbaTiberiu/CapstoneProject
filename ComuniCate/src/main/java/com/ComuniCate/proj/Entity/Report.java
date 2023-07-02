package com.ComuniCate.proj.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ComuniCate.proj.Enum.ReportType;
import com.ComuniCate.proj.Enum.StatusType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, name = "report_type")
    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;
    
    @Column(nullable = false)
    private LocalDate date = LocalDate.now();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "report")
    private List<Photo> photos = new ArrayList<>();
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType status = StatusType.INVIATA;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}  
    
    
    