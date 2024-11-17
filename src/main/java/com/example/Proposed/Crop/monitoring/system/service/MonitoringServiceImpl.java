package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dao.MonitoringLogDao;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.MonitoringLogDto;
import com.example.Proposed.Crop.monitoring.system.Dto.MonitoringLogStatus;
import com.example.Proposed.Crop.monitoring.system.Entity.Impl.MonitoringLogEntity;
import com.example.Proposed.Crop.monitoring.system.exception.DataPersistException;
import com.example.Proposed.Crop.monitoring.system.exception.MonitoringNotFoundException;
import com.example.Proposed.Crop.monitoring.system.util.AppUtil;
import com.example.Proposed.Crop.monitoring.system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    }

    @Override
    public void updateMonitoringLog(String logCode, MonitoringLogDto monitoringLogDTO) {

    }
}
