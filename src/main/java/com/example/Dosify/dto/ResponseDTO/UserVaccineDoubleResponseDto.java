package com.example.Dosify.dto.ResponseDTO;

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
public class UserVaccineDoubleResponseDto {
    String name;

    String mobNo;
      String doseNo1;
    VaccineType vaccineType1;
    String  doseNo2;
    VaccineType vaccineType2;
    Date appointmentDate1;
    Date appointmentDate2;
}
