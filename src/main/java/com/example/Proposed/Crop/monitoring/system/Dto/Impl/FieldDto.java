package com.example.Proposed.Crop.monitoring.system.Dto.Impl;

import com.example.Proposed.Crop.monitoring.system.Dto.FieldStatus;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.CropEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.StaffEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDto implements FieldStatus {
    private String field_code;
    private String field_name;
    private String location;
    private Double extent_size;
    private String field_image1;
    private String field_image2;
    private List<CropDto> crops;
    private List<StaffDto> allocated_staff;
}
