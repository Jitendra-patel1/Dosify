package com.example.Dosify.service;

import com.example.Dosify.Exception.UserNotFoundException;
import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserCommonResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;

import com.example.Dosify.dto.ResponseDTO.UserVaccineDoubleResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserVaccineSingleResponseDto;
import com.example.Dosify.model.User;

import java.util.List;

public interface UserService {

    public UserResponseDto addUser(UserRequestDto userRequestDto);

    UserCommonResponseDto getById(Integer id) throws UserNotFoundException;

    UserCommonResponseDto getByEmail(String email) throws UserNotFoundException;

    UserCommonResponseDto updatByMobNo(String mobNo, String email) throws UserNotFoundException;


    List<UserResponseDto> noDose();


    List<UserVaccineSingleResponseDto> singleDose();

    List<UserVaccineDoubleResponseDto> fullyVaccinated();

    List<UserVaccineSingleResponseDto> getOnlyMaleWithSingleDose();

    List<UserVaccineDoubleResponseDto> getFullyVaccinatedFemale();
}
