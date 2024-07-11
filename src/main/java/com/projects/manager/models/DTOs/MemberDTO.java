package com.projects.manager.models.DTOs;

import com.projects.manager.models.enums.Positions;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
public class MemberDTO {
    private Positions position;
    private long memberId;
}
