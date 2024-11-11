package com.example.Proposed.Crop.monitoring.system.controller;

import com.example.Proposed.Crop.monitoring.system.Dto.Impl.CropDto;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.FieldDto;
import com.example.Proposed.Crop.monitoring.system.exception.DataPersistException;
import com.example.Proposed.Crop.monitoring.system.service.CropService;
import com.example.Proposed.Crop.monitoring.system.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
public class CropController {
    @Autowired
    private CropService cropservice;
    public ResponseEntity<Void> saveCrop(
            @RequestPart("cropCode") String cropCode,
            @RequestPart("commonName") String commonName,
            @RequestPart("scientificName")String scientificName,
            @RequestPart("category") String category,
            @RequestPart("season") String season,
            @RequestPart("cropImage") MultipartFile cropImage,
            @RequestPart(" field")FieldDto field
            )
    {
        String base64cropImage_01 = "";
        try{
            byte [] bytesCropImage_01 = cropImage.getBytes();
            base64cropImage_01=AppUtil.cropImageToBase64(bytesCropImage_01);

            CropDto cropDto = new CropDto();

            cropDto.setCrop_code(cropCode);
            cropDto.setCommon_name(commonName);
            cropDto.setScientific_name(scientificName);
            cropDto.setCategory(category);
            cropDto.setSeason(season);
            cropDto.setCrop_image(base64cropImage_01);
            cropDto.setField(field);

            cropservice.saveCrop(cropDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}

