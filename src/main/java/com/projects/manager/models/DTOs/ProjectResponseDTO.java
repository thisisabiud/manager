package com.projects.manager.models.DTOs;

import com.projects.manager.models.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDTO {
    private  long id;
    private String title;
    private String description;
    private double requiredFund;
    private double availableFund;
    private ProjectStatus status;
    private double expectedProfit;
    private LocalDate from;
    private LocalDate to;
}
