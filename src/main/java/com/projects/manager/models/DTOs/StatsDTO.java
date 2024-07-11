package com.projects.manager.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatsDTO {
    private double expectedReturn;
    private double requiredFund;
    private double availableFund;
    private long projects;
    private long completed;
    private long pending;
    private long ongoing;
    private long female;
    private long male;
    private long users;
}
