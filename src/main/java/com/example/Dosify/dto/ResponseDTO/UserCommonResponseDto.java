package com.example.Dosify.dto.ResponseDTO;

import com.example.Dosify.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserCommonResponseDto {
    String name;
    String email;
    String mobNo;
    Gender gender;
    Integer age;
}
