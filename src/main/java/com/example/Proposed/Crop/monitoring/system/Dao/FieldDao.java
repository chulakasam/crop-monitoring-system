package com.example.Proposed.Crop.monitoring.system.Dao;

import com.example.Proposed.Crop.monitoring.system.Entity.Impl.FieldEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldDao extends JpaRepository<FieldEntity,String> {

    @Query("SELECT f FROM FieldEntity f WHERE f.field_code = :field_code")
    Optional<FieldEntity> findByFieldName(@Param("field_code") String field_code);

    @Query("SELECT f FROM FieldEntity f WHERE f.field_code IN :field_code")
    List<FieldEntity> findByFieldNameList(@Param("field_code") List<String> field_code);
}
