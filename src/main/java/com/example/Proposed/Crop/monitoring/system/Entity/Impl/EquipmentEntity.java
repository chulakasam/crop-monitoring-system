package com.example.Proposed.Crop.monitoring.system.Entity.Impl;

import com.example.Proposed.Crop.monitoring.system.Entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "equipment")
public class EquipmentEntity implements SuperEntity {
    @Id
    private String equipment_id;
    private String name;
    private String type;
    private String status;
    @ManyToOne
    @JoinColumn(name = "id")
    private StaffEntity assigned_staff;
    @ManyToOne
    @JoinColumn(name = "field_code")
    private FieldEntity assigned_field;
}
