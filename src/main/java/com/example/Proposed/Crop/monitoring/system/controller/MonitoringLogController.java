package com.example.Proposed.Crop.monitoring.system.controller;

import com.example.Proposed.Crop.monitoring.system.service.MonitoringLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/logs")
public class MonitoringLogController {
    @Autowired
    private MonitoringLogService monitoringLogService;
}
