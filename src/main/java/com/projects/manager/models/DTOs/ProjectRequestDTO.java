package com.projects.manager.models.DTOs;

import com.projects.manager.models.enums.ProjectStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ProjectRequestDTO {
    private String title;
    private String description;
    private double requiredFund;
    private double availableFund;
    private ProjectStatus status;
    private double expectedProfit;
    private List<MemberDTO> members;
    private LocalDate from;
    private LocalDate to;
}
