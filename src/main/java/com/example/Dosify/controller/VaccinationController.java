package com.example.Dosify.controller;

import com.example.Dosify.Enum.CenterType;
import com.example.Dosify.Enum.Gender;
import com.example.Dosify.dto.RequestDTO.VaccinationCenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.DoctorListResponseDto;
import com.example.Dosify.dto.ResponseDTO.VaccinationResponseDto;
import com.example.Dosify.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/center")
public class VaccinationController {
    @Autowired
    VaccinationService vaccinationService;

    @PostMapping("/add")
    public ResponseEntity addCenter(@RequestBody VaccinationCenterRequestDto vaccinationCenterRequestDto) {
        VaccinationResponseDto vaccinationResponseDto = vaccinationService.addCenter(vaccinationCenterRequestDto);
        return new ResponseEntity(vaccinationResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/getbyname")
    public ResponseEntity getByName(@RequestParam String name) {
        try {
            VaccinationResponseDto vaccinationResponseDto = vaccinationService.getByName(name);
            return new ResponseEntity(vaccinationResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/listofdoctor")
    public ResponseEntity listOfDoctor(@RequestParam Integer Id){
        try {
           List<DoctorListResponseDto> doctorListResponseDto = vaccinationService.listOfDoctor(Id);
            return new ResponseEntity(doctorListResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/listofcenter")
    public ResponseEntity listOfCenter(@RequestParam CenterType centerType){

        List<VaccinationResponseDto> list =vaccinationService.listOfCenter(centerType);
        return new ResponseEntity<>(list,HttpStatus.OK);

    }
    @GetMapping("/listofdoctorbygender")
    public ResponseEntity listOfDoctorByGender(@RequestParam Integer Id, @RequestParam Gender gender){
        try {
            List<DoctorListResponseDto> doctorListResponseDto = vaccinationService.listOfDoctorByGender(Id,gender);
            return new ResponseEntity(doctorListResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/listofdoctorbyage")
    public ResponseEntity listOfDoctorByGenderByAge(@RequestParam Integer Id, @RequestParam Gender gender,@RequestParam int age){
        try {
            List<DoctorListResponseDto> doctorListResponseDto = vaccinationService.listOfDoctorByGenderByAge(Id,gender,age);
            return new ResponseEntity(doctorListResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
