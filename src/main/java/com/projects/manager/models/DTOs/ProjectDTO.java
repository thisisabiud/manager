package com.projects.manager.models.DTOs;

import com.projects.manager.models.enums.ProjectStatus;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class ProjectDTO {
    private String title;
    private String description;
    private double expectedBudget;
    private double availableBudget;
    private ProjectStatus status;
    private double expectedProfit;
    private OffsetDateTime startingTime;
    private OffsetDateTime finishingTime;
}
