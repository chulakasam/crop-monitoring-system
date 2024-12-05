package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dao.CropDao;
import com.example.Proposed.Crop.monitoring.system.Dao.FieldDao;
import com.example.Proposed.Crop.monitoring.system.Dto.CropStatus;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.CropDto;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.FieldDto;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.CropEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.FieldEntity;
import com.example.Proposed.Crop.monitoring.system.customStatusCodes.SelectedUserErrorStatus;
import com.example.Proposed.Crop.monitoring.system.exception.CropNotFoundException;
import com.example.Proposed.Crop.monitoring.system.exception.DataPersistException;
import com.example.Proposed.Crop.monitoring.system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CropServiceImpl implements CropService{
    @Autowired
    private Mapping mapping;
    @Autowired
    private CropDao cropDao;

    @Autowired
    private FieldDao fieldDao;
    @Override
    public void saveCrop(CropDto cropDto) {
        CropEntity saveEntity = cropDao.save(mapping.toCropEntity(cropDto));
        if(saveEntity==null){
            throw new DataPersistException("Crop Not Found !!!");
        }
    }
    @Override
    public List<CropDto> getAllCrops() {
        List<CropEntity> crops = cropDao.findAll();
        return crops.stream()
                .map(crop -> {
                    CropDto cropDTO = new CropDto();
                    cropDTO.setCrop_code(crop.getCrop_code());
                    cropDTO.setCrop_image(crop.getCrop_image());
                    cropDTO.setCommon_name(crop.getCommon_name());
                    cropDTO.setScientific_name(crop.getScientific_name());
                    cropDTO.setCategory(crop.getCategory());
                    cropDTO.setSeason(crop.getSeason());
                    Optional<FieldEntity> assignedField = fieldDao.
                            findById(crop.getField().getField_code());
                    FieldDto assignedFieldDTO = assignedField.isPresent() ?
                            mapping.toFieldDTO(assignedField.get()) : null;
                    cropDTO.setField(assignedFieldDTO);
                    return cropDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CropStatus getCrop(String cropCode) {
        if(cropDao.existsById(cropCode)) {
            var selectedCrop = cropDao.getReferenceById(cropCode);
            return mapping.toCropDTO(selectedCrop);
        }else {
               return new SelectedUserErrorStatus(2,"Selected crop does not exist");
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> foundCrop = cropDao.findById(cropCode);
        if(!foundCrop.isPresent()) {
            throw new CropNotFoundException("Crop not found");
        }else {
            cropDao.deleteById(cropCode);
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDto cropDTO) {
        Optional<CropEntity> tmpCrop = cropDao.findById(cropCode);
        if(!tmpCrop.isPresent()) {
            throw new CropNotFoundException("Crop not found");
        }else {
            tmpCrop.get().setCommon_name(cropDTO.getCommon_name());
            tmpCrop.get().setScientific_name(cropDTO.getScientific_name());
            tmpCrop.get().setCrop_image(cropDTO.getCrop_image());
            tmpCrop.get().setCategory(cropDTO.getCategory());
            tmpCrop.get().setSeason(cropDTO.getSeason());
        }
    }
}
