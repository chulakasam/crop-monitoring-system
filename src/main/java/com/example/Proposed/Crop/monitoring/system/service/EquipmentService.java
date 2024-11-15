package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dto.EquipmentStatus;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.EquipmentDto;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDto equipmentDto);

    EquipmentStatus getEquipment(String equipmentId);

    List<EquipmentDto> getAllEquipment();

    void deleteEquipment(String equipmentId);

    void updateEquipment(String equipmentId, EquipmentDto equipmentDto);
}
