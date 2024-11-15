package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dto.FieldStatus;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.FieldDto;

public interface FieldService {
    void saveField(FieldDto fieldDto);

    FieldStatus getField(String fieldCode);

}
