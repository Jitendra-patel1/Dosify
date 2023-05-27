package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.CenterType;
import com.example.Dosify.Enum.Gender;
import com.example.Dosify.Exception.VaccationCenterNotFoundException;
import com.example.Dosify.Transformer.DoctorTransformer;
import com.example.Dosify.Transformer.VaccinationTransformer;
import com.example.Dosify.dto.RequestDTO.VaccinationCenterRequestDto;
import com.example.Dosify.dto.ResponseDTO.DoctorListResponseDto;
import com.example.Dosify.dto.ResponseDTO.VaccinationResponseDto;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.VaccinationRepository;
import com.example.Dosify.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VaccinationServiceImpl implements VaccinationService {
    @Autowired
    VaccinationRepository vaccinationRepository;

    @Override
    public VaccinationResponseDto addCenter(VaccinationCenterRequestDto vaccinationCenterRequestDto) {

        VaccinationCenter vaccinationCenter= VaccinationTransformer.DtoToEntity(vaccinationCenterRequestDto);
       VaccinationCenter vaccination=vaccinationRepository.save(vaccinationCenter);
       return VaccinationTransformer.EntityToDto(vaccination);
    }

    @Override
    public VaccinationResponseDto getByName(String name) throws VaccationCenterNotFoundException {
        if(vaccinationRepository.findByName(name)==null){
            throw new VaccationCenterNotFoundException("vaccination center for this name is not present search for other");
        }
        VaccinationCenter vaccinationCenter=vaccinationRepository.findByName(name);
        return VaccinationTransformer.EntityToDto(vaccinationCenter);

    }

    @Override
    public List<DoctorListResponseDto> listOfDoctor(Integer id) {
        VaccinationCenter vaccinationCenter=vaccinationRepository.findById(id).get();
        List<Doctor> list = vaccinationCenter.getDoctors();
        List<DoctorListResponseDto> doctorListResponseDtos=new ArrayList<>();
        for(Doctor doctor:list){
            DoctorListResponseDto doctorListResponseDto= DoctorTransformer.ListDoctorToDTo(doctor);
            doctorListResponseDtos.add(doctorListResponseDto);
        }
        return doctorListResponseDtos;
    }

    @Override
    public List<VaccinationResponseDto> listOfCenter(CenterType centerType) {
        List<VaccinationCenter> list =vaccinationRepository.findAll();
        List<VaccinationResponseDto> vaccinationResponseDtos= new ArrayList<>();
        for(VaccinationCenter vaccinationCenter:list){
            if(vaccinationCenter.getCenterType()==centerType){
                VaccinationResponseDto vaccinationResponseDto=VaccinationTransformer.EntityToDto(vaccinationCenter);
                vaccinationResponseDtos.add(vaccinationResponseDto);
            }
        }
        return vaccinationResponseDtos;
    }

    @Override
    public List<DoctorListResponseDto> listOfDoctorByGender(Integer id, Gender gender) {
        VaccinationCenter vaccinationCenter=vaccinationRepository.findById(id).get();
        List<Doctor> list = vaccinationCenter.getDoctors();
        List<DoctorListResponseDto> doctorListResponseDtos=new ArrayList<>();
        for(Doctor doctor:list) {
            if (doctor.getGender() == gender) {
                DoctorListResponseDto doctorListResponseDto = DoctorTransformer.ListDoctorToDTo(doctor);
                doctorListResponseDtos.add(doctorListResponseDto);
            }
        }
        return doctorListResponseDtos;
    }

    @Override
    public List<DoctorListResponseDto> listOfDoctorByGenderByAge(Integer id, Gender gender, int age) {
        VaccinationCenter vaccinationCenter=vaccinationRepository.findById(id).get();
        List<Doctor> list = vaccinationCenter.getDoctors();
        List<DoctorListResponseDto> doctorListResponseDtos=new ArrayList<>();
        for(Doctor doctor:list) {
            if (doctor.getGender() == gender && doctor.getAge()==age) {
                DoctorListResponseDto doctorListResponseDto = DoctorTransformer.ListDoctorToDTo(doctor);
                doctorListResponseDtos.add(doctorListResponseDto);
            }
        }
        return doctorListResponseDtos;
    }


}
