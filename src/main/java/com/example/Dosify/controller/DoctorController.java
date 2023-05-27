package com.example.Dosify.controller;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.Exception.VaccationCenterNotFoundException;
import com.example.Dosify.dto.RequestDTO.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDTO.DoctorListResponseDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.dto.ResponseDTO.RatioResponseDto;
import com.example.Dosify.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto) {
        try {
            DoctorResponseDto doctorResponseDto = doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity<>(doctorResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getbymobno")
    public ResponseEntity getByMobNo(@RequestParam String mobNo){
        try{
            DoctorListResponseDto  doctorResponseDto =doctorService.getByMobNo(mobNo);
            return new ResponseEntity(doctorResponseDto,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);

        }
    }
    @PutMapping("/updatebyemail")
    public  ResponseEntity updateDoctor(@RequestParam String email,@RequestParam String mobNo){
        try{
            DoctorListResponseDto doctorResponseDto=doctorService.updateDoctor(email,mobNo);
            return new ResponseEntity(doctorResponseDto,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_by_age_and_name")
    public ResponseEntity getByAgeAndName(@RequestParam Gender gender,@RequestParam int age){
        try{
            List<DoctorListResponseDto> doctorListResponseDtos=doctorService.getByAgeAndName(gender,age);
            return new ResponseEntity<>(doctorListResponseDtos,HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/doctor_by_range")
    public ResponseEntity doctorBYRange(@RequestParam Integer startAge,@RequestParam Integer endAge){
        try{
            List<DoctorListResponseDto> doctorListResponseDtos=doctorService.doctorByRange(startAge,endAge);
            return new ResponseEntity<>(doctorListResponseDtos,HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getbyname")
    public ResponseEntity doctorDetails(@RequestParam String name) {
       try {
           DoctorListResponseDto doctorResponseDto = doctorService.getByname(name);
           return new ResponseEntity(doctorResponseDto, HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);

       }
   }
    @GetMapping("/doctorbyappointment")
    public ResponseEntity doctorAppointment(@RequestParam Integer number) {
       List<DoctorListResponseDto> doctorListResponseDtos = doctorService.doctorAppointment(number);
       return new ResponseEntity<>(doctorListResponseDtos, HttpStatus.OK);
   }


    @GetMapping("/doctorbyageandgender")
    public ResponseEntity doctorByAgeAndGender(@RequestParam Integer age,@RequestParam Gender gender){
        List<DoctorListResponseDto> doctorListResponseDtos = doctorService.doctorByAgeAndGender(age,gender);
        return new ResponseEntity<>(doctorListResponseDtos, HttpStatus.OK);

    }
    @GetMapping("/getratio")
    public  ResponseEntity getRatio() {
        RatioResponseDto ratio=doctorService.getRatio();
        return new ResponseEntity(ratio,HttpStatus.OK);
    }

   }

