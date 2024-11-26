package com.example.Proposed.Crop.monitoring.system.controller;

import com.example.Proposed.Crop.monitoring.system.Dto.CropStatus;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.CropDto;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.FieldDto;
import com.example.Proposed.Crop.monitoring.system.customStatusCodes.SelectedUserErrorStatus;
import com.example.Proposed.Crop.monitoring.system.exception.CropNotFoundException;
import com.example.Proposed.Crop.monitoring.system.exception.DataPersistException;
import com.example.Proposed.Crop.monitoring.system.service.CropService;
import com.example.Proposed.Crop.monitoring.system.service.FieldService;
import com.example.Proposed.Crop.monitoring.system.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/crop")
@CrossOrigin(origins = "http://localhost:63342")

public class CropController {
    @Autowired
    private CropService cropservice;

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(
            @RequestParam("cropCode") String cropCode,
            @RequestParam("cropName") String cropName,
            @RequestParam("scientificName")String scientificName,
            @RequestParam("category") String category,
            @RequestParam("season") String season,
            @RequestPart("image") MultipartFile image,
            @RequestParam("field_code")String field_code
            ) {
        String base64cropImage_01 = "";
        try {
           FieldDto field= fieldService.getFieldByName(field_code);
            byte[] bytesCropImage_01 = image.getBytes();
            base64cropImage_01 = AppUtil.cropImageToBase64(bytesCropImage_01);

            CropDto cropDto = new CropDto();

            cropDto.setCrop_code(cropCode);
            cropDto.setCommon_name(cropName);
            cropDto.setScientific_name(scientificName);
            cropDto.setCategory(category);
            cropDto.setSeason(season);
            cropDto.setCrop_image(base64cropImage_01);
            cropDto.setField(field);

            cropservice.saveCrop(cropDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        @GetMapping(value = "/{crop_code}", produces = MediaType.APPLICATION_JSON_VALUE)
        public CropStatus getSelectedCrop(@PathVariable ("crop_code") String crop_code){
            return cropservice.getCrop(crop_code);
        }

        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public List<CropDto> getAllCrops(){
        return cropservice.getAllCrops();
    }
        @DeleteMapping(value = "/{crop_code}")
        public ResponseEntity<Void> deleteCrop(@PathVariable ("crop_code") String crop_code){
        try {
            cropservice.deleteCrop(crop_code);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

       }
        @PutMapping(value = "/{cropCode}")
        public ResponseEntity<Void> updateCrop(@PathVariable ("cropCode") String cropCode,
            @RequestBody CropDto cropDto){
            try {

            cropservice.updateCrop(cropCode, cropDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

