package com.example.Proposed.Crop.monitoring.system.Dao;

import com.example.Proposed.Crop.monitoring.system.Entity.Impl.MonitoringLogEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDao extends JpaRepository<StaffEntity,String> {
}
