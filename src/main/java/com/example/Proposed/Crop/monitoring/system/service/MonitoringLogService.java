package com.example.Proposed.Crop.monitoring.system.service;

import com.example.Proposed.Crop.monitoring.system.Dto.Impl.MonitoringLogDto;
import com.example.Proposed.Crop.monitoring.system.Dto.MonitoringLogStatus;

import java.util.List;

public interface MonitoringLogService {
    void saveMonitoringLog(MonitoringLogDto monitoringLogDTO);

    List<MonitoringLogDto> getAllMonitoringLogs();

    MonitoringLogStatus getMonitoringLog(String logCode);

    void deleteMonitoringLog(String logCode);

    void updateMonitoringLog(String logCode,MonitoringLogDto monitoringLogDTO);
}
