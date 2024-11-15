package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dto.Impl.StaffDto;
import com.example.Proposed.Crop.monitoring.system.Dto.StaffStatus;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDto staffDto);

    List<StaffDto> getAllStaff();

    StaffStatus getStaff(String id);

    void deleteStaff(String id);

    void updateStaff(String id,StaffDto staffDto);
}
