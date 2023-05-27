package com.example.Dosify.Transformer;

import com.example.Dosify.dto.RequestDTO.AppointmentRequestDto;
import com.example.Dosify.dto.ResponseDTO.AppointmentResponseDto;
import com.example.Dosify.model.Appointment;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class AppointmentTransformer {
    public static Appointment EntityToDto(AppointmentRequestDto appointmentRequestDto){
        return Appointment.builder()
                .appointmentNo(UUID.randomUUID().toString())
                .doseNo(appointmentRequestDto.getDoseNo())
                .dateOfAppointment(appointmentRequestDto.getDateOfAppointment())
                .build();
    }
    public static AppointmentResponseDto DtoToEntity(Appointment appointment){
        return AppointmentResponseDto.builder()
                .appointmentNo(appointment.getAppointmentNo())
                .doseNo(appointment.getDoseNo())
                .doctorId(appointment.getDoctor().getId())
                .userId(appointment.getUser().getId())
                .build();
    }
}
