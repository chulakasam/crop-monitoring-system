package com.example.Proposed.Crop.monitoring.system.Entity.Impl;

import com.example.Proposed.Crop.monitoring.system.Entity.SuperEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Field")
public class FieldEntity implements SuperEntity {
    @Id
    private String field_code;
    private String field_name;
    private String location;
    private Double extent_size;
    @Column(columnDefinition = "LONGTEXT")
    private String field_image1;
    @Column(columnDefinition = "LONGTEXT")
    private String field_image2;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    @JsonManagedReference

    private List<CropEntity> crops;
    @ManyToMany(mappedBy = "fields")
    @JsonManagedReference
    private List<StaffEntity> allocated_staff;
}
