package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dto.Impl.VehicleDto;
import com.example.Proposed.Crop.monitoring.system.Dto.VehicleStatus;

import java.util.List;

public interface VehicleService {
    void saveVehicle(VehicleDto vehicleDTO);

    List<VehicleDto> getAllVehicles();

    VehicleStatus getVehicle(String vehicleCode);

    void deleteVehicle(String vehicleCode);

    void updateVehicle(String vehicleCode,VehicleDto vehicleDTO);
}
