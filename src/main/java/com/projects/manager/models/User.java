package com.projects.manager.models;

import com.projects.manager.models.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstname;
    private String lastname;
    private LocalDate birth;
    private Gender gender;
    @OneToMany(mappedBy = "member")
    private Set<Team> projects;
    @CreatedDate
    private OffsetDateTime created_at;
    private OffsetDateTime updated_at;

    private String getAge(){
        LocalDate today = LocalDate.now();
        return String.valueOf(Period.between(today, birth).getYears());
    }

    public String getFullName(){
        return firstname + " " + lastname;
    }
}
