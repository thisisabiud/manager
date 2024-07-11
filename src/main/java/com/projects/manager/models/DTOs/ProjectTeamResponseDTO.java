package com.projects.manager.models.DTOs;

import com.projects.manager.models.enums.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTeamResponseDTO {
    private ProjectResponseDTO project;
    private List<MemberDTO> team;
}
