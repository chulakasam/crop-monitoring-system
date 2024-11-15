package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dao.EquipmentDao;
import com.example.Proposed.Crop.monitoring.system.Dto.EquipmentStatus;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.EquipmentDto;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.EquipmentEntity;
import com.example.Proposed.Crop.monitoring.system.customStatusCodes.SelectedUserErrorStatus;
import com.example.Proposed.Crop.monitoring.system.exception.DataPersistException;
import com.example.Proposed.Crop.monitoring.system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService{
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveEquipment(EquipmentDto equipmentDto) {
        EquipmentEntity saveEquipment = equipmentDao.save(mapping.toEquipmentEntity(equipmentDto));
        if(saveEquipment==null){
            throw new DataPersistException("Equipment not saved !!!");
        }
    }

    @Override
    public EquipmentStatus getEquipment(String equipmentId) {
        if (equipmentDao.existsById(equipmentId)) {
            var selectedEquipment = equipmentDao.getReferenceById(equipmentId);
            return mapping.toEquipmentDTO(selectedEquipment);
        } else {
            return new SelectedUserErrorStatus(2,"Selected Equipment not found");
        }
    }

    @Override
    public List<EquipmentDto> getAllEquipment() {
        return mapping.toEquipmentDTOList(equipmentDao.findAll());
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<EquipmentEntity> foundEquipment = equipmentDao.findById(equipmentId);
        if(!foundEquipment.isPresent()){
            throw new DataPersistException("Equipment not found !!!");
        }else{
            equipmentDao.deleteById(equipmentId);
        }
    }


}
