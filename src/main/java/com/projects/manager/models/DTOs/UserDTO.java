package com.projects.manager.models.DTOs;

import com.projects.manager.models.enums.Gender;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDTO {
    private String firstname;
    private String lastname;
    private LocalDate birth;
    private Gender gender;
}
