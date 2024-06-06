package com.projects.manager.models;

import com.projects.manager.models.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @CreatedDate
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String title;
    private String description;
    private double expectedBudget;
    private double availableBudget;
    private ProjectStatus status;
    private double expectedProfit;
    @OneToMany(mappedBy = "project")
    private Set<Team> members;
    private OffsetDateTime startingTime;
    private OffsetDateTime finishingTime;
}
