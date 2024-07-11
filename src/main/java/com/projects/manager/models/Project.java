package com.projects.manager.models;

import com.projects.manager.models.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private double requiredFund;
    private double availableFund;
    private ProjectStatus status;
    private double expectedProfit;
    private LocalDate starting;
    private LocalDate finishing;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Team> members;

    @OneToMany(mappedBy = "taskProject", cascade = CascadeType.ALL)
    private List<Task> tasks;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
