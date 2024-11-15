package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dto.Impl.StaffDto;
import com.example.Proposed.Crop.monitoring.system.Dto.StaffStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService{
    @Override
    public void saveStaff(StaffDto staffDto) {

    }

    @Override
    public List<StaffDto> getAllStaff() {
        return null;
    }

    @Override
    public StaffStatus getStaff(String id) {
        return null;
    }

    @Override
    public void deleteStaff(String id) {

    }

    @Override
    public void updateStaff(String id, StaffDto staffDto) {

    }
}
