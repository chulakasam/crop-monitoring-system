package com.example.Proposed.Crop.monitoring.system.Dto;

import com.example.Proposed.Crop.monitoring.system.Entity.Impl.FieldEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.VehicleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDto implements StaffStatus{
    private String id;
    private String first_name;
    private String last_name;
    private String designation;
    private String gender;
    private String joined_date;
    private String dob;
    private String address;
    private String contact_no;
    private String email;
    private String role;
    private List<FieldEntity> fields;
    private List<VehicleEntity> vehicles;
}
