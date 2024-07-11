package com.projects.manager.models;


import com.projects.manager.models.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private ProjectStatus status;
    private double allocatedBudget;
    private OffsetDateTime begin;
    private OffsetDateTime endDate;
//    private OffsetDateTime end;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    private Project taskProject;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
