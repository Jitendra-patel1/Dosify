package com.example.Dosify.Transformer;

import com.example.Dosify.dto.RequestDTO.VaccinationCenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.VaccinationResponseDto;
import com.example.Dosify.model.VaccinationCenter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VaccinationTransformer {

    public static VaccinationCenter DtoToEntity(VaccinationCenterRequestDto vaccinationCenterRequestDto){
        return VaccinationCenter.builder()
                .name(vaccinationCenterRequestDto.getName())
                .location(vaccinationCenterRequestDto.getLocation())
                .centerType(vaccinationCenterRequestDto.getCenterType())
                .build();
    }
    public static VaccinationResponseDto EntityToDto(VaccinationCenter vaccinationCenter){
        return VaccinationResponseDto.builder()
                .name(vaccinationCenter.getName())
                .centerType(vaccinationCenter.getCenterType())
                .location(vaccinationCenter.getLocation())
                .build();
    }
}
