package com.example.Proposed.Crop.monitoring.system.Dto;

import com.example.Proposed.Crop.monitoring.system.Entity.Impl.CropEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.FieldEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.StaffEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDto implements MonitoringLogStatus{

    private String log_code;
    private String log_date;
    private String log_details;
    private String observed_image;
    private List<FieldEntity> fields;
    private List<CropEntity> crops;
    private List<StaffEntity> staff;
}
