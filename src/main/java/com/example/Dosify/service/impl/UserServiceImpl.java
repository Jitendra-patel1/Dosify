package com.example.Dosify.service.impl;

import com.example.Dosify.Exception.UserNotFoundException;
import com.example.Dosify.Transformer.UserTransformer;
import com.example.Dosify.dto.RequestDTO.UserRequestDto;
import com.example.Dosify.dto.ResponseDTO.UserCommonResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserResponseDto;
//import com.example.Dosify.dto.ResponseDTO.UserVaccineDoubleResponseDto;
//import com.example.Dosify.dto.ResponseDTO.UserVaccineSingleResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserVaccineDoubleResponseDto;
import com.example.Dosify.dto.ResponseDTO.UserVaccineSingleResponseDto;
import com.example.Dosify.model.User;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.Dosify.Enum.Gender.FEMALE;
import static com.example.Dosify.Enum.Gender.MALE;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {

     User user= UserTransformer.DtoToUser(userRequestDto);
     User newuser=userRepository.save(user);

        //entity -> response dto
       return UserTransformer.userTODto(newuser);

    }

    @Override
    public UserCommonResponseDto getById(Integer id) throws UserNotFoundException {
        if(userRepository.findById(id)==null){
            throw new UserNotFoundException("User for this id is not present please enter valid id");
        }
        User user=userRepository.findById(id).get();
        UserCommonResponseDto userCommonResponseDto=UserTransformer.UserTODto(user);
        return userCommonResponseDto;

    }

    @Override
    public UserCommonResponseDto getByEmail(String email) throws UserNotFoundException {
        if(userRepository.findByEmailId(email)==null){
            throw new UserNotFoundException("User for this emailid is not present please enter valid emailid");
        }
        User user=userRepository.findByEmailId(email);
        UserCommonResponseDto userCommonResponseDto=UserTransformer.UserTODto(user);
        return userCommonResponseDto;
     }

    @Override
    public UserCommonResponseDto updatByMobNo(String mobNo, String email) throws UserNotFoundException {
        if(userRepository.findByMobNo(mobNo)==null){
            throw new UserNotFoundException("User for this mobile number is not present please enter valid mobile number");
        }
        User user=userRepository.findByMobNo(mobNo);
        user.setEmailId(email);
        UserCommonResponseDto userCommonResponseDto=UserTransformer.UserTODto(user);
        return userCommonResponseDto;

    }

    @Override
    public List<UserResponseDto> noDose() {
        List<User> list =userRepository.findAll();
        List<UserResponseDto> response = new ArrayList<>();
        for(User users:list){
            if(users.isDose1Taken()==false && users.isDose2Taken()==false){

                UserResponseDto userResponseDto=UserTransformer.DoseToDto(users);
                response.add(userResponseDto);
            }
        }
        return response;
    }

    @Override
    public List<UserVaccineSingleResponseDto> singleDose() {
        List<User> list =userRepository.findAll();
        List<UserVaccineSingleResponseDto> response = new ArrayList<>();
        for(User users:list){
            if(users.isDose1Taken()==true && users.isDose2Taken()==false){

                UserVaccineSingleResponseDto userResponseDto=UserTransformer.SingleDoseToDto(users);
                response.add(userResponseDto);
            }
        }
        return response;
    }

    @Override
    public List<UserVaccineDoubleResponseDto> fullyVaccinated() {
        List<User> list =userRepository.findAll();
        List<UserVaccineDoubleResponseDto> response = new ArrayList<>();
        for(User users:list){
            if(users.isDose1Taken()==true && users.isDose2Taken()==true){

                UserVaccineDoubleResponseDto userVaccineDoubleResponseDto=UserTransformer.DoubleDoseToDto(users);
                response.add(userVaccineDoubleResponseDto);
            }
        }
        return response;
    }

    @Override
    public List<UserVaccineSingleResponseDto> getOnlyMaleWithSingleDose() {
       List<UserVaccineSingleResponseDto> list =new ArrayList<>();
        List<User> users=userRepository.findAll();
        for(User user:users){
            if(user.getGender()==MALE && user.isDose1Taken()==true && user.isDose2Taken()==false){
                UserVaccineSingleResponseDto userVaccineSingleResponseDto=UserTransformer.SingleDoseToDto(user);
                list.add(userVaccineSingleResponseDto);
            }
        }
        return list;


    }

    @Override
    public List<UserVaccineDoubleResponseDto> getFullyVaccinatedFemale() {
        List<UserVaccineDoubleResponseDto> list =new ArrayList<>();
        List<User> users=userRepository.findAll();
        for(User user:users){
            if(user.getGender()==FEMALE && user.isDose2Taken()==true && user.isDose2Taken()==true){
                UserVaccineDoubleResponseDto userVaccineDoubleResponseDto=UserTransformer.DoubleDoseToDto(user);
                list.add(userVaccineDoubleResponseDto);
            }
        }
        return list;
    }
}
