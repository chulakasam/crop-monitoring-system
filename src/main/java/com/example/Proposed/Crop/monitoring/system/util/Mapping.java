package com.example.Proposed.Crop.monitoring.system.util;

import com.example.Proposed.Crop.monitoring.system.Dto.Impl.CropDto;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.FieldDto;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.VehicleDto;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.CropEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.FieldEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.VehicleEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public FieldEntity toFieldEntity(FieldDto fieldDto){
        return modelMapper.map(fieldDto, FieldEntity.class);
    }
    public FieldDto toFieldDTO(FieldEntity fieldEntity){
        return modelMapper.map(fieldEntity, FieldDto.class);
    }

    public List<FieldDto> asFieldDTOList(List<FieldEntity> fieldEntityList){return modelMapper.map(fieldEntityList, new TypeToken<List<FieldDto>>(){}.getType());
    }

    public CropEntity toCropEntity(CropDto cropDto) {
        return modelMapper.map(cropDto, CropEntity.class);
    }
    public CropDto toCropDTO(CropEntity cropEntity){
        return modelMapper.map(cropEntity, CropDto.class);
    }
    public List<CropDto> asCropDTOList(List<CropEntity> cropEntityList){
        return modelMapper.map(cropEntityList, new TypeToken<List<CropDto>>(){}.getType());
    }


    public VehicleEntity toVehicleEntity(VehicleDto vehicleDTO) {
        return modelMapper.map(vehicleDTO, VehicleEntity.class);
    }

    public VehicleDto toVehicleDTO(VehicleEntity vehicleEntity) {
        return modelMapper.map(vehicleEntity, VehicleDto.class);
    }

    public List<VehicleDto> toVehicleDTOList(List<VehicleEntity> vehicleEntitiesList) {
        return modelMapper.map(vehicleEntitiesList,new TypeToken<List<VehicleDto>>() {}.getType());
    }
}
