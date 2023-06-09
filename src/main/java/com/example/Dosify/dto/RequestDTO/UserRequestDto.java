package com.example.Dosify.dto.RequestDTO;


import com.example.Dosify.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserRequestDto {

    String name;

    int age;

    String emailId;

    String mobNo;

    Gender gender;
}
