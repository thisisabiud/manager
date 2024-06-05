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
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String position;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private User member;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @CreatedDate
    private OffsetDateTime created_at;
    private OffsetDateTime updated_at;
}
