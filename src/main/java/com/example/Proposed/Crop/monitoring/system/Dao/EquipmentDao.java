package com.example.Proposed.Crop.monitoring.system.Dao;

import com.example.Proposed.Crop.monitoring.system.Entity.Impl.EquipmentEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentDao extends JpaRepository<EquipmentEntity,String> {
}
