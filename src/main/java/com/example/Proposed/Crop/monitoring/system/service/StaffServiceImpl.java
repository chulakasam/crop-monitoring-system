package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dao.FieldDao;
import com.example.Proposed.Crop.monitoring.system.Dao.StaffDao;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.FieldDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffServiceImpl implements StaffService{
    @Autowired
    private Mapping mapping;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private FieldDao fieldDao;
    @Override
    public void saveStaff(StaffDto staffDto) {
        StaffEntity staffEntity = staffDao.save(mapping.toStaffEntity(staffDto));
        if(staffEntity==null){
            throw new StaffNotFoundException("Staff Not saved !!!");
        }
    }

    @Override
    public List<StaffDto> getAllStaff() {
        List<StaffEntity> staffs = staffDao.findAll();
        return staffs.stream()
                .map(staff -> {
                    StaffDto staffDTO = new StaffDto();
                    staffDTO.setId(staff.getId());
                    staffDTO.setFirst_name(staff.getFirst_name());
                    staffDTO.setLast_name(staff.getLast_name());
                    staffDTO.setDesignation(staff.getDesignation());
                    staffDTO.setGender(staff.getGender());
                    staffDTO.setJoined_date(staff.getJoined_date());
                    staffDTO.setDob(staff.getDob());
                    staffDTO.setAddress(staff.getAddress());
                    staffDTO.setContact_no(staff.getContact_no());
                    staffDTO.setEmail(staff.getEmail());
                    staffDTO.setRole(staff.getRole());
                    List<FieldDto> assignedFieldDTO = new ArrayList<>();
                    for (FieldEntity field : staff.getFields()) {
                        Optional<FieldEntity> fieldOpt = fieldDao.findById(field.getField_name());
                        if (fieldOpt.isPresent()) {
                            assignedFieldDTO.add(mapping.toFieldDTO(fieldOpt.get()));
                        }
                    }
                    staffDTO.setFields(assignedFieldDTO);
                    return staffDTO;
                })
                .collect(Collectors.toList());
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
        Optional<StaffEntity> staffEntity = staffDao.findById(id);
        if(!staffEntity.isPresent()){
            throw new StaffNotFoundException("Staff Not Found !!!");
        }else {
            staffDao.deleteById(id);
        }
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
            //List<FieldEntity> fieldEntityList = mapping.toFieldEntityList(staffDto.getFields());
            //temporyStaff.get().setFields(fieldEntityList);
            //List<VehicleEntity> vehicleEntityList = mapping.toVehicleEntityList(staffDto.getVehicles());
            //temporyStaff.get().setVehicles(vehicleEntityList);
        }
    }
}
