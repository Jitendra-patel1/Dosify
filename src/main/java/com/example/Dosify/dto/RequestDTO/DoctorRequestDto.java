package com.example.Dosify.dto.RequestDTO;

import com.example.Dosify.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DoctorRequestDto {
    Integer id;
    String name;
    String email;
    String mobNo;

   int age;
   Gender gender;
}
