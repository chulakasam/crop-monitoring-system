package com.example.Proposed.Crop.monitoring.system.util;

import com.example.Proposed.Crop.monitoring.system.Dto.Impl.*;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.*;
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
    public List<FieldEntity> toFieldEntityList(List<FieldDto> fieldDTOList) {
        return modelMapper.map(fieldDTOList,new TypeToken<List<FieldEntity>>() {}.getType());
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
    public List<CropEntity> toCropEntityList(List<CropDto> cropDTOList) {
        return modelMapper.map(cropDTOList,new TypeToken<List<CropEntity>>() {}.getType());
    }


    public VehicleEntity toVehicleEntity(VehicleDto vehicleDTO) {
        return modelMapper.map(vehicleDTO, VehicleEntity.class);
    }

    public VehicleDto toVehicleDTO(VehicleEntity vehicleEntity) {
        return modelMapper.map(vehicleEntity, VehicleDto.class);
    }

    public List<VehicleDto> toVehicleDTOList(List<VehicleEntity> vehicleEntitiesList) {return modelMapper.map(vehicleEntitiesList,new TypeToken<List<VehicleDto>>() {}.getType());
    }
    public List<VehicleEntity> toVehicleEntityList(List<VehicleDto> vehicleDTOList){
        return modelMapper.map(vehicleDTOList,new TypeToken<List<VehicleEntity>>() {}.getType());
    }

    public EquipmentEntity toEquipmentEntity(EquipmentDto equipmentDto) {
        return modelMapper.map(equipmentDto, EquipmentEntity.class);
    }
    public EquipmentDto toEquipmentDTO(EquipmentEntity equipmentEntity) {
        return modelMapper.map(equipmentEntity, EquipmentDto.class);
    }

    public List<EquipmentDto> toEquipmentDTOList(List<EquipmentEntity> all)  {return modelMapper.map(all,new TypeToken<List<EquipmentDto>>() {}.getType());
    }


    public StaffEntity toStaffEntity(StaffDto staffDTO) {
        return modelMapper.map(staffDTO, StaffEntity.class);
    }

    public StaffDto toStaffDTO(StaffEntity staffEntity) {
        return modelMapper.map(staffEntity, StaffDto.class);
    }

    public List<StaffDto> toStaffDTOList(List<StaffEntity> staffEntitiesList) {
        return modelMapper.map(staffEntitiesList,new TypeToken<List<StaffDto>>() {}.getType());
    }
    public List<StaffEntity> toStaffEntityList(List<StaffDto> staffDTOList) {
        return modelMapper.map(staffDTOList,new TypeToken<List<StaffEntity>>() {}.getType());
    }


    public MonitoringLogEntity toMonitoringLogEntity(MonitoringLogDto monitoringLogDTO) {
        return modelMapper.map(monitoringLogDTO, MonitoringLogEntity.class);
    }

    public MonitoringLogDto toMonitoringLogDTO(MonitoringLogEntity logEntity) {
        return modelMapper.map(logEntity, MonitoringLogDto.class);
    }

    public List<MonitoringLogDto> toMonitoringLogDTOList(List<MonitoringLogEntity> logEntitiesList) {
        return modelMapper.map(logEntitiesList,new TypeToken<List<MonitoringLogDto>>() {}.getType());
    }
    public UserEntity toUserEntity(UserDto userDTO){
        return modelMapper.map(userDTO, UserEntity.class);
    }
    public UserDto toUserDTO(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDto.class);
    }
    public List<UserDto> asUserDTOList(List<UserEntity> userEntities) {
        return modelMapper.map(userEntities, new TypeToken<List<UserDto>>() {}.getType());
    }
}
