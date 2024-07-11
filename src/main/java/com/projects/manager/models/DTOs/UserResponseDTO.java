package com.projects.manager.models.DTOs;

import com.projects.manager.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private  String age;
    private Gender gender;
    private String birth;
}
