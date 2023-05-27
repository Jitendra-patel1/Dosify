package com.example.Dosify.Transformer;

import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserCommonResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
//import com.example.Dosify.dto.ResponseDTO.UserVaccineDoubleResponseDto;
//import com.example.Dosify.dto.ResponseDTO.UserVaccineSingleResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserVaccineDoubleResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserVaccineSingleResponseDto;
import com.example.Dosify.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserTransformer {
    public static UserResponseDto userTODto(User user){
        return UserResponseDto.builder()
                .name(user.getName())
                .message("Your are login done successfully")
                .build();
    }

    public static User DtoToUser(UserRequestDto userRequestDto){
       return  User.builder()
                .age(userRequestDto.getAge())
                .mobNo(userRequestDto.getMobNo())
                .emailId(userRequestDto.getEmailId())
                .gender(userRequestDto.getGender())
                .name(userRequestDto.getName())
                .build();
    }
    public static UserCommonResponseDto UserTODto(User user){
        return UserCommonResponseDto.builder()
                .age(user.getAge())
                .email(user.getEmailId())
                .gender(user.getGender())
                .mobNo(user.getMobNo())
                .name(user.getName())
                .build();
    }
    public static  UserResponseDto DoseToDto(User user){
        return UserResponseDto.builder()
                .name(user.getName())
                .message("You have not taken a single dose of vaccine")
                .build();
    }

    public static UserResponseDto onlyDoseToDto(User users) {
        return UserResponseDto.builder()
                .name(users.getName())
                .message("You have only taken single dose please take your second dose on time").build();
    }

    public static UserResponseDto FullyToDto(User users) {
        return UserResponseDto.builder()
                .name(users.getName())
                .message("Congratulation you are fully vaccinated!!!!")
                .build();
    }
    public static UserVaccineSingleResponseDto SingleDoseToDto(User user){
        return UserVaccineSingleResponseDto.builder()
                .name(user.getName())
                .mobNo(user.getMobNo())
                .vaccineType(user.getDose1().getVaccineType())
                .appointmentDate(user.getDose1().getVaccinationDate())
                .build();
    }
    public static UserVaccineDoubleResponseDto DoubleDoseToDto(User user){
        return UserVaccineDoubleResponseDto.builder()
                .name(user.getName())
                .mobNo(user.getMobNo())
                .doseNo1(user.getDose1().getDoseId())
                .vaccineType1(user.getDose1().getVaccineType())
                .doseNo2(user.getDose2().getDoseId())
                .vaccineType2(user.getDose2().getVaccineType())
                .appointmentDate1(user.getDose1().getVaccinationDate())
                .appointmentDate2(user.getDose2().getVaccinationDate())
                .build();
    }

}
