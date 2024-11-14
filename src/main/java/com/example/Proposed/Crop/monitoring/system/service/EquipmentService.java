package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dto.EquipmentStatus;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.EquipmentDto;

public interface EquipmentService {
    void saveEquipment(EquipmentDto equipmentDto);

    EquipmentStatus getEquipment(String equipmentId);
}
