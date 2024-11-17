package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dao.MonitoringLogDao;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.MonitoringLogDto;
import com.example.Proposed.Crop.monitoring.system.Dto.MonitoringLogStatus;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.CropEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.FieldEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.MonitoringLogEntity;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.StaffEntity;
import com.example.Proposed.Crop.monitoring.system.exception.DataPersistException;
import com.example.Proposed.Crop.monitoring.system.exception.MonitoringNotFoundException;
import com.example.Proposed.Crop.monitoring.system.util.AppUtil;
import com.example.Proposed.Crop.monitoring.system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MonitoringServiceImpl implements MonitoringLogService{
    @Autowired
    private MonitoringLogDao monitoringLogDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveMonitoringLog(MonitoringLogDto monitoringLogDTO) {
        monitoringLogDTO.setLog_code(AppUtil.generateLogId());
        MonitoringLogEntity saveLog = monitoringLogDao.save(mapping.toMonitoringLogEntity(monitoringLogDTO));
        if(saveLog == null){
            throw new DataPersistException("Monitoring Log not saved");
        }
    }

    @Override
    public List<MonitoringLogDto> getAllMonitoringLogs() {
        return mapping.toMonitoringLogDTOList(monitoringLogDao.findAll());
    }

    @Override
    public MonitoringLogStatus getMonitoringLog(String logCode) {
        if(monitoringLogDao.existsById(logCode)){
            var selectedLog = monitoringLogDao.getReferenceById(logCode);
            return mapping.toMonitoringLogDTO(selectedLog);
        }else{
            throw new MonitoringNotFoundException("monitoring log not found");
        }
    }

    @Override
    public void deleteMonitoringLog(String logCode) {
        Optional<MonitoringLogEntity> foundLog = monitoringLogDao.findById(logCode);
        if(foundLog.isPresent()){
            throw new MonitoringNotFoundException("Log not found");
        }else{
            monitoringLogDao.deleteById(logCode);
        }
    }

    @Override
    public void updateMonitoringLog(String logCode, MonitoringLogDto monitoringLogDTO) {
        Optional<MonitoringLogEntity> tmpLog = monitoringLogDao.findById(logCode);
        if(!tmpLog.isPresent()){
            throw new MonitoringNotFoundException("Log not found");
        }else{
            tmpLog.get().setLog_date(monitoringLogDTO.getLog_date());
            tmpLog.get().setLog_details(monitoringLogDTO.getLog_details());
            tmpLog.get().setObserved_image(monitoringLogDTO.getObserved_image());
            List<FieldEntity> fieldEntityList = mapping.toFieldEntityList(monitoringLogDTO.getFields());
            tmpLog.get().setFields(fieldEntityList);
            List<CropEntity> cropEntityList = mapping.toCropEntityList(monitoringLogDTO.getCrops());
            tmpLog.get().setCrops(cropEntityList);
            List<StaffEntity> staffEntityList = mapping.toStaffEntityList(monitoringLogDTO.getStaff());
            tmpLog.get().setStaff(staffEntityList);
        }
    }
}
