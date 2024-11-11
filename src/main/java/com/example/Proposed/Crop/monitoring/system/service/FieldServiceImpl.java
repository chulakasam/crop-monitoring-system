package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dao.FieldDao;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.FieldDto;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.FieldEntity;
import com.example.Proposed.Crop.monitoring.system.exception.FieldNotFoundException;
import com.example.Proposed.Crop.monitoring.system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

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
}
