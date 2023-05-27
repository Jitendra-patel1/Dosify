package com.example.Dosify.dto.ResponseDTO;

import com.example.Dosify.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DoctorResponseDto {
    String name;
    String email;
    String mobNo;

   VaccinationResponseDto vaccinationResponseDto;
}
