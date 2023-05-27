package com.example.Dosify.service.impl;

import com.example.Dosify.Enum.DoseNo;
import com.example.Dosify.Exception.*;
import com.example.Dosify.Transformer.AppointmentTransformer;

import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;

import com.example.Dosify.model.*;
import com.example.Dosify.repository.AppointmentRepository;
import com.example.Dosify.repository.DoctorRepository;
import com.example.Dosify.repository.UserRepository;
import com.example.Dosify.service.AppointmentService;
import com.example.Dosify.service.Dose1Service;
import com.example.Dosify.service.Dose2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.example.Dosify.Enum.DoseNo.DOSE_1;
import static com.example.Dosify.Enum.VaccineType.COVISHIELD;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    Dose1Service dose1Service;
    @Autowired
    Dose2Service dose2Service;

    @Autowired
    private JavaMailSender emailSender;


    @Override
    public AppointmentResponseDto addAppointment(AppointmentRequestDto appointmentRequestDto) throws Exception {
     Optional<User> optionalUser=userRepository.findById(appointmentRequestDto.getUserId());
     if(optionalUser.isEmpty()){
         throw new UserNotFoundException("User doesn't exists");
     }
     Optional<Doctor> optionalDoctor=doctorRepository.findById(appointmentRequestDto.getDoctorId());
     if(optionalDoctor.isEmpty()){
         throw new DoctorNotFoundException("Doctor doesn't exists");
     }
     User user=optionalUser.get();
     Doctor doctor=optionalDoctor.get();

     if(appointmentRequestDto.getDoseNo()== DoseNo.DOSE_1 ){
         if(user.isDose1Taken()==true){
             throw new DoseAlreadyTaken("you have already taken dose-1 go for dose-2");
         }
         Dose1 dose1=dose1Service.createDose1(user,appointmentRequestDto.getVaccineType());
         user.setDose1Taken(true);
         user.setDose1(dose1);
     }
     else if(appointmentRequestDto.getDoseNo()==DoseNo.DOSE_2){
         if(user.isDose1Taken()==false){
             throw new DoseNotTakenException("You have not taken dose-1");
         }
         if(user.isDose2Taken()==true){
             throw  new DoseAlreadyTaken("your all dose is taken ");
         }
         Dose2 dose2=dose2Service.createDose2(user,appointmentRequestDto.getVaccineType());
         user.setDose2Taken(true);
         user.setDose2(dose2);
     }
      Appointment appointment=AppointmentTransformer.EntityToDto(appointmentRequestDto);
     appointment.setDoctor(doctor);
     appointment.setUser(user);
      user.getAppointments().add(appointment);
      //doctor.getAppointments().add(appointment); not using this line save to appointmet for different appointmentid
      User savedUser=userRepository.save(user); //save dose1/dose2 and appointment
        Appointment savedAppointment=savedUser.getAppointments().get(savedUser.getAppointments().size()-1);
        doctor.getAppointments().add(savedAppointment);
        doctorRepository.save(doctor);

        //send email;
        String text="Congrats "+user.getName()+" your "+appointment.getDoseNo()+" has been booked !!" +"\n"
                +"AppointmentNo : "+appointment.getAppointmentNo()+"\n"
                +"DoctorName : "+doctor.getName()+"\n"
                +"CenterAddress : "+doctor.getVaccinationCenter().getName()+","+"\n"
                                   +doctor.getVaccinationCenter().getLocation();


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("springboot066@gmail.com");
        message.setTo(user.getEmailId()); //from the user
        message.setSubject("Appointment Booked !!!");
        message.setText(text);
        emailSender.send(message);


      return AppointmentTransformer.DtoToEntity(appointment);
    }
}
