package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dao.StaffDao;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.StaffDto;
import com.example.Proposed.Crop.monitoring.system.Dto.StaffStatus;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.FieldEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.StaffEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.VehicleEntity;
import com.example.Proposed.Crop.monitoring.system.customStatusCodes.SelectedUserErrorStatus;
import com.example.Proposed.Crop.monitoring.system.exception.StaffNotFoundException;
import com.example.Proposed.Crop.monitoring.system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements StaffService{
    @Autowired
    private Mapping mapping;
    @Autowired
    private StaffDao staffDao;
    @Override
    public void saveStaff(StaffDto staffDto) {
        StaffEntity staffEntity = staffDao.save(mapping.toStaffEntity(staffDto));
        if(staffEntity==null){
            throw new StaffNotFoundException("Staff Not saved !!!");
        }
    }

    @Override
    public List<StaffDto> getAllStaff() {
        return mapping.toStaffDTOList(staffDao.findAll());
    }

    @Override
    public StaffStatus getStaff(String id) {
        if(staffDao.existsById(id)){
            var selectedStaff = staffDao.getReferenceById(id);
            return mapping.toStaffDTO(selectedStaff);
        }else {
            return new SelectedUserErrorStatus(2,"Selected Staff Member Not Found");
        }
    }

    @Override
    public void deleteStaff(String id) {


    }

    @Override
    public void updateStaff(String id, StaffDto staffDto) {
        Optional<StaffEntity> temporyStaff = staffDao.findById(id);
        if(!temporyStaff.isPresent()) {
            throw new StaffNotFoundException("Staff Member Not Found");
        }else{
            temporyStaff.get().setFirst_name(staffDto.getFirst_name());
            temporyStaff.get().setLast_name(staffDto.getLast_name());
            temporyStaff.get().setDesignation(staffDto.getDesignation());
            temporyStaff.get().setGender(staffDto.getGender());
            temporyStaff.get().setJoined_date(staffDto.getJoined_date());
            temporyStaff.get().setDob(staffDto.getDob());
            temporyStaff.get().setAddress(staffDto.getAddress());
            temporyStaff.get().setContact_no(staffDto.getContact_no());
            temporyStaff.get().setEmail(staffDto.getEmail());
            temporyStaff.get().setRole(staffDto.getRole());
            List<FieldEntity> fieldEntityList = mapping.toFieldEntityList(staffDto.getFields());
            temporyStaff.get().setFields(fieldEntityList);
            List<VehicleEntity> vehicleEntityList = mapping.toVehicleEntityList(staffDto.getVehicles());
            temporyStaff.get().setVehicles(vehicleEntityList);
        }
    }
}
