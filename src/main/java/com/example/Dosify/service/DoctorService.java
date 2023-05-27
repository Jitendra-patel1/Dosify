package com.example.Dosify.service;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.Exception.DoctorNotFoundException;
import com.example.Dosify.Exception.VaccationCenterNotFoundException;
import com.example.Dosify.dto.RequestDTO.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDTO.DoctorListResponseDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.dto.ResponseDTO.RatioResponseDto;

import java.util.List;

public interface DoctorService {
    DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws VaccationCenterNotFoundException;

    DoctorListResponseDto getByMobNo(String mobNo) throws DoctorNotFoundException;

    DoctorListResponseDto  updateDoctor(String email, String mobNo) throws DoctorNotFoundException;

    List<DoctorListResponseDto> getByAgeAndName(Gender gender, int age) throws DoctorNotFoundException;

    List<DoctorListResponseDto> doctorByRange(Integer startAge, Integer endAge);

    DoctorListResponseDto getByname(String name) throws DoctorNotFoundException;

    List<DoctorListResponseDto> doctorAppointment(Integer number);

    List<DoctorListResponseDto> doctorByAgeAndGender(Integer age, Gender gender);

    RatioResponseDto getRatio();
}
