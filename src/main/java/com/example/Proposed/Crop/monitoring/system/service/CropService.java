package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dto.CropStatus;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.CropDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CropService {
    void saveCrop(CropDto cropDto);
    List<CropDto> getAllCrops();

    CropStatus getCrop(String cropCode);

    void deleteCrop(String cropCode);

    void updateCrop(String cropCode,CropDto cropDTO);
}
