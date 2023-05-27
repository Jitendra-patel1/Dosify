package com.example.Dosify.Transformer;

import com.example.Dosify.dto.RequestDTO.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDTO.DoctorListResponseDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;

import com.example.Dosify.dto.ResponseDTO.RatioResponseDto;
import com.example.Dosify.dto.ResponseDTO.VaccinationResponseDto;
import com.example.Dosify.model.Doctor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DoctorTransformer {
    public  static DoctorResponseDto DoctorToDto(Doctor doctor){
        VaccinationResponseDto vaccinationResponseDto=VaccinationTransformer.EntityToDto(doctor.getVaccinationCenter());

        return DoctorResponseDto.builder()
                .name(doctor.getName())
                .email(doctor.getEmailId())
                .mobNo(doctor.getMobNo())
                .vaccinationResponseDto(vaccinationResponseDto)
                .build();
    }
    public static Doctor DtoToEntity(DoctorRequestDto doctorRequestDto){
        return  Doctor.builder()
                .name(doctorRequestDto.getName())
                .emailId(doctorRequestDto.getEmail())
                .mobNo(doctorRequestDto.getMobNo())
                .age(doctorRequestDto.getAge())
                .gender(doctorRequestDto.getGender()).build();
    }
    public static DoctorListResponseDto ListDoctorToDTo(Doctor doctor){
        return DoctorListResponseDto.builder()
                .name(doctor.getName())
                .mobNo(doctor.getMobNo())
                .age(doctor.getAge())
                .gender(doctor.getGender())
                .build();
    }
  public  static RatioResponseDto ratioDto(int male,int female){
        return RatioResponseDto.builder()
                .ratio(male+" Male"+ " : " +female+" Female")
                .build();
  }
}
