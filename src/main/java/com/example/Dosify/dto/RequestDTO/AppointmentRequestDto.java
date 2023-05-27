package com.example.Dosify.dto.RequestDTO;

import com.example.Dosify.Enum.DoseNo;
import com.example.Dosify.Enum.VaccineType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AppointmentRequestDto {
    String appointmentNo;
    int userId;
    int doctorId;
    DoseNo doseNo;
    VaccineType vaccineType;
    Date dateOfAppointment;
}
