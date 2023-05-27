package com.example.Dosify.service;

import com.example.Dosify.Exception.DoctorNotFoundException;
import com.example.Dosify.Exception.Dose1NotTakenException;
import com.example.Dosify.Exception.UserNotFoundException;
import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;

public interface AppointmentService {
    AppointmentResponseDto addAppointment(AppointmentRequestDto appointmentRequestDto) throws Exception;
}
