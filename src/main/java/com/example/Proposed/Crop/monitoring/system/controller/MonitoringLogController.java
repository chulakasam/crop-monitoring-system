package com.example.Proposed.Crop.monitoring.system.controller;

import com.example.Proposed.Crop.monitoring.system.Dto.Impl.CropDto;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.FieldDto;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.MonitoringLogDto;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.StaffDto;
import com.example.Proposed.Crop.monitoring.system.Dto.MonitoringLogStatus;
import com.example.Proposed.Crop.monitoring.system.exception.DataPersistException;
import com.example.Proposed.Crop.monitoring.system.exception.MonitoringNotFoundException;
import com.example.Proposed.Crop.monitoring.system.service.MonitoringLogService;
import com.example.Proposed.Crop.monitoring.system.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/logs")
public class MonitoringLogController {
    @Autowired
    private MonitoringLogService monitoringLogService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveMonitoringLog(
                                        @RequestPart("logDate") String logDate,
                                        @RequestPart ("logDetails") String logDetails,
                                        @RequestPart ("observedImage") MultipartFile observedImage,
                                        @RequestPart ("fields") List<FieldDto> fields,
                                        @RequestPart ("crops") List<CropDto> crops,
                                        @RequestPart ("staff") List<StaffDto> staff
    )
    {
        String base64ObservedImage = "";
        try {
            byte[] byteObservedImage = observedImage.getBytes();
            base64ObservedImage = AppUtil.observedImageOneToBase64(byteObservedImage);

            String log_code = AppUtil.generateLogId();

            MonitoringLogDto buildMonitoringLogDTO = new MonitoringLogDto();
            buildMonitoringLogDTO.setLog_code(log_code);
            buildMonitoringLogDTO.setLog_date(logDate);
            buildMonitoringLogDTO.setLog_details(logDetails);
            buildMonitoringLogDTO.setObserved_image(base64ObservedImage);
            buildMonitoringLogDTO.setFields(fields);
            buildMonitoringLogDTO.setCrops(crops);
            buildMonitoringLogDTO.setStaff(staff);
            monitoringLogService.saveMonitoringLog(buildMonitoringLogDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{logCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public MonitoringLogStatus getSelectedLog(@PathVariable ("logCode") String logCode){
        return monitoringLogService.getMonitoringLog(logCode);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitoringLogDto> getAllLogs(){
        return monitoringLogService.getAllMonitoringLogs();
    }
    @DeleteMapping(value = "/{logCode}")
    public ResponseEntity<Void> deleteLog(@PathVariable ("logCode") String logCode){
        try {

            monitoringLogService.deleteMonitoringLog(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (MonitoringNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{logCode}")
    public ResponseEntity<Void> updateLog(@PathVariable ("logCode") String logCode,
                                          @RequestBody MonitoringLogDto monitoringLogDTO){
        try {
            monitoringLogService.updateMonitoringLog(logCode,monitoringLogDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (MonitoringNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
