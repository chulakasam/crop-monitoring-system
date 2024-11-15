package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dao.FieldDao;
import com.example.Proposed.Crop.monitoring.system.Dto.FieldStatus;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.FieldDto;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.FieldEntity;
import com.example.Proposed.Crop.monitoring.system.customStatusCodes.SelectedUserErrorStatus;
import com.example.Proposed.Crop.monitoring.system.exception.DataPersistException;
import com.example.Proposed.Crop.monitoring.system.exception.FieldNotFoundException;
import com.example.Proposed.Crop.monitoring.system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

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
        return mapping.asFieldDTOList(fieldDao.findAll());
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


}
