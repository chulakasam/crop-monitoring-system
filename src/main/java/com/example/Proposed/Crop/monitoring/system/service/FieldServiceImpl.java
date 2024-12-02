package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dao.FieldDao;
import com.example.Proposed.Crop.monitoring.system.Dto.FieldStatus;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.FieldDto;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.CropEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.FieldEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.StaffEntity;
import com.example.Proposed.Crop.monitoring.system.customStatusCodes.SelectedUserErrorStatus;
import com.example.Proposed.Crop.monitoring.system.exception.DataPersistException;
import com.example.Proposed.Crop.monitoring.system.exception.FieldNotFoundException;
import com.example.Proposed.Crop.monitoring.system.util.AppUtil;
import com.example.Proposed.Crop.monitoring.system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FieldServiceImpl implements FieldService{
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveField(FieldDto fieldDto) {

        FieldEntity fieldEntity = fieldDao.save(mapping.toFieldEntity(fieldDto));
        if(fieldEntity==null){
            throw new FieldNotFoundException("Field not found !!!!");
        }
    }

    @Override
    public FieldStatus getField(String fieldCode) {
        if(fieldDao.existsById(fieldCode)){
            FieldEntity selectedField = fieldDao.getReferenceById(fieldCode);
            return mapping.toFieldDTO(selectedField);
        }else {
            return new SelectedUserErrorStatus(2,"Selected Field not found !!!");
        }
    }

    @Override
    public List<FieldDto> getAllFields() {
        List<FieldEntity> fields = fieldDao.findAll();
        return fields.stream()
                .map(field -> {
                    FieldDto fieldDTO = new FieldDto();
                    fieldDTO.setField_code(field.getField_code());
                    fieldDTO.setField_name(field.getField_name());
                    fieldDTO.setLocation(field.getLocation());
                    fieldDTO.setExtent_size(field.getExtent_size());
                    fieldDTO.setField_image1(field.getField_image1());
                    fieldDTO.setField_image2(field.getField_image2());

                    return fieldDTO;
                })
                .collect(Collectors.toList());


    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> selectedField = fieldDao.findById(fieldCode);
        if(!selectedField.isPresent()){
            throw new FieldNotFoundException("Field not found!!!");
        }else{
            fieldDao.deleteById(fieldCode);
        }
    }

    @Override
    public void updateField(String fieldCode, FieldDto fieldDto) {
        Optional<FieldEntity> fieldEntity = fieldDao.findById(fieldCode);
        if(!fieldEntity.isPresent()){
            throw new FieldNotFoundException("Field not found !!!!");
        }else{
            fieldEntity.get().setField_name(fieldDto.getField_name());
            fieldEntity.get().setLocation(fieldDto.getLocation());
            fieldEntity.get().setExtent_size(fieldDto.getExtent_size());
            fieldEntity.get().setField_image1(fieldDto.getField_image1());
            fieldEntity.get().setField_image2(fieldDto.getField_image2());
            List<CropEntity> cropEntityList = mapping.toCropEntityList(fieldDto.getCrops());
            fieldEntity.get().setCrops(cropEntityList);
            List<StaffEntity> staffEntityList = mapping.toStaffEntityList(fieldDto.getAllocated_staff());
            fieldEntity.get().setAllocated_staff(staffEntityList);
        }
    }

    @Override
    public FieldDto getFieldByName(String field_code) {
        Optional<FieldEntity> tmpField = fieldDao.findByFieldName(field_code);
        if(!tmpField.isPresent()){
            throw new FieldNotFoundException("Field not found: " + field_code);
        }
        return mapping.toFieldDTO(tmpField.get());
    }

    @Override
    public List<FieldDto> getFieldListByName(List<String> field_code) {
        if(field_code == null || field_code.isEmpty()){
            return Collections.emptyList();
        }

        List<FieldEntity> fieldEntities = fieldDao.findByFieldNameList(field_code);

        if(fieldEntities.isEmpty()){
            throw new FieldNotFoundException("Field not found");
        }

        return fieldEntities.stream()
                .map(mapping::toFieldDTO)
                .collect(Collectors.toList());

    }

}