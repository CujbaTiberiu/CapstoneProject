package com.ComuniCate.proj.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import com.ComuniCate.proj.Entity.Photo;
import com.ComuniCate.proj.Enum.ReportType;
import com.ComuniCate.proj.Enum.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

    private ReportType reportType;
    private String description;
    private String latitude;
    private String longitude;
    private LocalDate date = LocalDate.now();
    private List<Photo> photos = new ArrayList<>();
    private StatusType status = StatusType.INVIATA;
    private String username;
}
