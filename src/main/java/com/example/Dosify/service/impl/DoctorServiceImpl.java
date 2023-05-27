package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.Gender;
import com.example.Dosify.Exception.DoctorNotFoundException;
import com.example.Dosify.Exception.VaccationCenterNotFoundException;
import com.example.Dosify.Transformer.DoctorTransformer;
import com.example.Dosify.dto.RequestDTO.DoctorRequestDto;
import com.example.Dosify.dto.ResponseDTO.DoctorListResponseDto;
import com.example.Dosify.dto.ResponseDTO.DoctorResponseDto;
import com.example.Dosify.dto.ResponseDTO.RatioResponseDto;
import com.example.Dosify.model.Doctor;
import com.example.Dosify.model.VaccinationCenter;
import com.example.Dosify.repository.DoctorRepository;
import com.example.Dosify.repository.VaccinationRepository;
import com.example.Dosify.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VaccinationRepository vaccinationRepository;

    @Override
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws VaccationCenterNotFoundException {
        if(vaccinationRepository.findById(doctorRequestDto.getId())==null){
            throw  new VaccationCenterNotFoundException("Vaccation center is not present");
        }
        VaccinationCenter vaccinationCenter=vaccinationRepository.findById(doctorRequestDto.getId()).get();
        Doctor doctor= DoctorTransformer.DtoToEntity(doctorRequestDto);
        doctor.setVaccinationCenter(vaccinationCenter);
        vaccinationCenter.getDoctors().add(doctor);
       vaccinationRepository.save(vaccinationCenter);


        return DoctorTransformer.DoctorToDto(doctor);
    }

    @Override
    public DoctorListResponseDto  getByMobNo(String mobNo) throws DoctorNotFoundException {
        if(doctorRepository.findByMobNo(mobNo)==null){
            throw new DoctorNotFoundException("Doctor not present for this mobile number please enter valid number");
        }
        Doctor doctor=doctorRepository.findByMobNo(mobNo);
        return DoctorTransformer.ListDoctorToDTo(doctor);
    }

    @Override
    public DoctorListResponseDto  updateDoctor(String email, String mobNo) throws DoctorNotFoundException {
        if(doctorRepository.findByEmailId(email)==null){
            throw new DoctorNotFoundException("Doctor not present for this emailId please enter valid emailId");
        }
        Doctor doctor=doctorRepository.findByEmailId(email);
        doctor.setMobNo(mobNo);

        Doctor newdoctor=doctorRepository.save(doctor);
        return DoctorTransformer.ListDoctorToDTo(newdoctor);
    }

    @Override
    public List<DoctorListResponseDto> getByAgeAndName(Gender gender, int age) throws DoctorNotFoundException {
        if(doctorRepository.findByGenderAndAge(gender,age)==null){
            throw new DoctorNotFoundException("No doctor found of this gender and age");
        }
        List<Doctor> doctors=doctorRepository.findByGenderAndAge(gender,age);
        List<DoctorListResponseDto> doctorListResponseDtos=new ArrayList<>();
        for(Doctor doctor:doctors){
         DoctorListResponseDto doctorListResponseDto=DoctorTransformer.ListDoctorToDTo(doctor);
         doctorListResponseDtos.add(doctorListResponseDto);
        }
        return doctorListResponseDtos;
    }

    @Override
    public List<DoctorListResponseDto> doctorByRange(Integer startAge, Integer endAge) {
        List<Doctor> doctors=doctorRepository.findAllAge(startAge,endAge);
        List<DoctorListResponseDto> doctorListResponseDtos=new ArrayList<>();
        for(Doctor doctor:doctors){
            DoctorListResponseDto doctorListResponseDto=DoctorTransformer.ListDoctorToDTo(doctor);
            doctorListResponseDtos.add(doctorListResponseDto);
        }
        return doctorListResponseDtos;
    }

    @Override
    public DoctorListResponseDto getByname(String name) throws DoctorNotFoundException {
        if(doctorRepository.findByName(name)==null){
            throw new DoctorNotFoundException("Doctor not present for this name please enter valid name");
        }
        Doctor doctor=doctorRepository.findByName(name);
        return DoctorTransformer.ListDoctorToDTo(doctor);
    }

    @Override
    public List<DoctorListResponseDto> doctorAppointment(Integer number) {
        List<Doctor> list=doctorRepository.findAll();
        List<DoctorListResponseDto> doctorListResponseDtos=new ArrayList<>();
        for(Doctor doctor: list){
            if(doctor.getAppointments().size()==number){
                DoctorListResponseDto doctorListResponseDto=DoctorTransformer.ListDoctorToDTo(doctor);
                doctorListResponseDtos.add(doctorListResponseDto);
            }
        }
        return doctorListResponseDtos;
    }

    @Override
    public List<DoctorListResponseDto> doctorByAgeAndGender(Integer age, Gender gender) {
        List<Doctor> list=doctorRepository.findAll();
        List<DoctorListResponseDto> doctorListResponseDtos=new ArrayList<>();
        for(Doctor doctor: list){
            if(doctor.getGender()==gender && doctor.getAge()==age){
                DoctorListResponseDto doctorListResponseDto=DoctorTransformer.ListDoctorToDTo(doctor);
                doctorListResponseDtos.add(doctorListResponseDto);
            }
        }
        return doctorListResponseDtos;

    }

    @Override
    public RatioResponseDto getRatio() {
        List<Doctor> list=doctorRepository.findAll();
        int male=0;
        int female=0;
        for(Doctor doctor:list){
            if(doctor.getGender()==Gender.MALE){
                male++;
            }
            if(doctor.getGender()==Gender.FEMALE){
                female++;
            }
        }
       return DoctorTransformer.ratioDto(male,female);
    }


}


