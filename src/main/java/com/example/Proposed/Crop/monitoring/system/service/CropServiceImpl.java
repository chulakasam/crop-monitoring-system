package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dao.CropDao;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.CropDto;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.CropEntity;
import com.example.Proposed.Crop.monitoring.system.exception.DataPersistException;
import com.example.Proposed.Crop.monitoring.system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CropServiceImpl implements CropService{
    @Autowired
    private Mapping mapping;
    @Autowired
    private CropDao cropDao;

    @Override
    public void saveCrop(CropDto cropDto) {
        CropEntity saveEntity = cropDao.save(mapping.toCropEntity(cropDto));
        if(saveEntity==null){
            throw new DataPersistException("Crop Not Found !!!");
        }
    }
}
