package com.example.Proposed.Crop.monitoring.system.Dto.Impl;

import com.example.Proposed.Crop.monitoring.system.Dto.EquipmentStatus;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.FieldEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.StaffEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDto implements EquipmentStatus {
    private String equipment_id;
    private String name;
    private String type;
    private String status;
    private StaffDto assigned_staff;
    private FieldDto assigned_field;
}
