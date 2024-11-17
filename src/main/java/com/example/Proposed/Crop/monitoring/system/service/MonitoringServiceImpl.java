package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dto.Impl.MonitoringLogDto;
import com.example.Proposed.Crop.monitoring.system.Dto.MonitoringLogStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MonitoringServiceImpl implements MonitoringLogService{

    @Override
    public void saveMonitoringLog(MonitoringLogDto monitoringLogDTO) {

    }

    @Override
    public List<MonitoringLogDto> getAllMonitoringLogs() {
        return null;
    }

    @Override
    public MonitoringLogStatus getMonitoringLog(String logCode) {
        return null;
    }

    @Override
    public void deleteMonitoringLog(String logCode) {

    }

    @Override
    public void updateMonitoringLog(String logCode, MonitoringLogDto monitoringLogDTO) {

    }
}
