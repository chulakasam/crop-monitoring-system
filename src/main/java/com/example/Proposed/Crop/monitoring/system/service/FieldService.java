package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dto.FieldStatus;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.FieldDto;

import java.util.List;

public interface FieldService {
    void saveField(FieldDto fieldDto);

    FieldStatus getField(String fieldCode);

    List<FieldDto> getAllFields();

    void deleteField(String fieldCode);

    void updateField(String fieldCode, FieldDto fieldDto);

    FieldDto getFieldByName(String field_code);
}
