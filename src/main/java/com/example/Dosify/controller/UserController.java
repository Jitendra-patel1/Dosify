package com.example.Dosify.controller;


import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserCommonResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;

import com.example.Dosify.dto.ResponseDTO.UserVaccineDoubleResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserVaccineSingleResponseDto;
import com.example.Dosify.model.User;
import com.example.Dosify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){

        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        return new ResponseEntity(userResponseDto, HttpStatus.CREATED);
    }
    @GetMapping("/getbyid")
    public ResponseEntity getById(@RequestParam Integer id){
        try{
            UserCommonResponseDto userCommonResponseDto=userService.getById(id);
            return new ResponseEntity(userCommonResponseDto,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getbyemail")
    public ResponseEntity getByEmailId(@RequestParam String email){
        try{
            UserCommonResponseDto userCommonResponseDto=userService.getByEmail(email);
            return new ResponseEntity(userCommonResponseDto,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

   @PutMapping("/updatebymobno")
   public ResponseEntity updateByMobNo(@RequestParam String mobNo,@RequestParam String email){
       try{
           UserCommonResponseDto userCommonResponseDto=userService.updatByMobNo(mobNo,email);
           return new ResponseEntity(userCommonResponseDto,HttpStatus.OK);
       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
   }

   @GetMapping("/no_single_dose")
    public ResponseEntity getByDose(){
       List<UserResponseDto> list= userService.noDose();
       return new ResponseEntity(list,HttpStatus.OK);

   }
   @GetMapping("/only_dose1")
       public ResponseEntity onlyDose(){
           List<UserVaccineSingleResponseDto> list= userService.singleDose();
           return new ResponseEntity(list,HttpStatus.OK);

       }

   @GetMapping("/fully_vaccinated")
   public ResponseEntity fullyVaccinated(){
       List<UserVaccineDoubleResponseDto> list= userService.fullyVaccinated();
       return new ResponseEntity(list,HttpStatus.OK);

   }
   @GetMapping("/get_male")
    public ResponseEntity getOnlyMaleWithSingleDose(){
        List<UserVaccineSingleResponseDto> list=userService.getOnlyMaleWithSingleDose();
       return new ResponseEntity(list,HttpStatus.OK);

   }
   @GetMapping("/get_female")
    public ResponseEntity getFullyVaccinatedFemale(){
        List<UserVaccineDoubleResponseDto> list=userService.getFullyVaccinatedFemale();
        return new ResponseEntity(list,HttpStatus.OK);
   }



}
