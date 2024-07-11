package com.projects.manager.models;

import com.projects.manager.models.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.data.annotation.CreatedDate;

import java.lang.reflect.Type;
import java.sql.Types;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birth;
    private Gender gender;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Team> projects;
    @CreatedDate
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public String getAge(){
        LocalDate today = LocalDate.now();
        return String.valueOf(Period.between(birth, today).getYears());
    }

    public String getFullName(){
        return firstname + " " + lastname;
    }
}
