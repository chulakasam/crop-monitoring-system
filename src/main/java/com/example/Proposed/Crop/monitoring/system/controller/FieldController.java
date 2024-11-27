package com.example.Proposed.Crop.monitoring.system.controller;

import com.example.Proposed.Crop.monitoring.system.Dto.FieldStatus;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.CropDto;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.FieldDto;
import com.example.Proposed.Crop.monitoring.system.Dto.Impl.StaffDto;
import com.example.Proposed.Crop.monitoring.system.exception.CropNotFoundException;
import com.example.Proposed.Crop.monitoring.system.exception.DataPersistException;
import com.example.Proposed.Crop.monitoring.system.exception.FieldNotFoundException;
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
@RequestMapping("api/v1/field")
@CrossOrigin(origins = "http://localhost:63342")

public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
//            @RequestParam("filedCode") String fieldCode,
            @RequestParam("fieldName") String fieldName,
            @RequestParam("extentSize")String extentSize,
            @RequestParam("location") String location,
            @RequestPart("fieldImage_01")MultipartFile fieldImage_01,
            @RequestPart("fieldImage_02")MultipartFile fieldImage_02,
            @RequestPart (value = "crops[]",required = false) List<CropDto> crops,
            @RequestPart (value = "staff[]",required = false) List<StaffDto> staff
            )
    {
        String base64fieldImage_01 = "";
        String base64fieldImage_02 = "";
        double extendSize=Double.parseDouble(extentSize);
        try{
            String fieldCode = AppUtil.generateFieldId();
            byte [] bytesFieldImage_01 = fieldImage_01.getBytes();
            base64fieldImage_01 = AppUtil.fieldImageOneToBase64(bytesFieldImage_01);

            byte [] bytesFieldImage_02 = fieldImage_02.getBytes();
            base64fieldImage_02 = AppUtil.fieldImageTwoToBase64(bytesFieldImage_02);


            FieldDto fieldDto = new FieldDto();

            fieldDto.setField_code(fieldCode);
            fieldDto.setField_name(fieldName);
            fieldDto.setExtent_size(extendSize);
            fieldDto.setLocation(location);
            fieldDto.setField_image1(base64fieldImage_01);
            fieldDto.setField_image2(base64fieldImage_02);
            fieldDto.setCrops(crops);
            fieldDto.setAllocated_staff(staff);


            fieldService.saveField(fieldDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/{fieldCode}",produces = MediaType.APPLICATION_JSON_VALUE)
    public FieldStatus getSelectedField(@PathVariable ("fieldCode") String fieldCode){
        return fieldService.getField(fieldCode);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldDto> getAllFields(){
        return fieldService.getAllFields();
    }


    @DeleteMapping(value = "/{fieldCode}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldCode") String fieldCode){
        try {
           fieldService.deleteField(fieldCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CropNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = "/{fieldCode}")
    public ResponseEntity<Void> updateField(@PathVariable ("fieldCode") String fieldCode,
                                            @RequestBody FieldDto fieldDto)
    {
        try {

            fieldService.updateField(fieldCode, fieldDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (FieldNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
