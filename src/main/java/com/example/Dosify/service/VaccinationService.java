package com.example.Dosify.service;

import com.example.Dosify.Enum.CenterType;
import com.example.Dosify.Enum.Gender;
import com.example.Dosify.Exception.VaccationCenterNotFoundException;
import com.example.Dosify.dto.RequestDTO.VaccinationCenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.DoctorListResponseDto;
import com.example.Dosify.dto.ResponseDTO.VaccinationResponseDto;

import java.util.List;

public interface VaccinationService {
    VaccinationResponseDto addCenter(VaccinationCenterRequestDto vaccinationCenterRequestDto);

    VaccinationResponseDto getByName(String name) throws VaccationCenterNotFoundException;
   List<DoctorListResponseDto> listOfDoctor(Integer id);

    List<VaccinationResponseDto> listOfCenter(CenterType centerType);

    List<DoctorListResponseDto> listOfDoctorByGender(Integer id, Gender gender);


    List<DoctorListResponseDto> listOfDoctorByGenderByAge(Integer id, Gender gender, int age);
}
