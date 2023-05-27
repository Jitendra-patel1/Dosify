package com.example.Dosify.controller;

import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Dosify.model.Appointment;
import com.example.Dosify.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

       @PostMapping("/add")
        public ResponseEntity addAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto){
           try{
               AppointmentResponseDto appointmentResponseDto=appointmentService.addAppointment(appointmentRequestDto);
               return new ResponseEntity<>(appointmentResponseDto, HttpStatus.CREATED);
           }catch (Exception e){
              return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
           }

    }
}
