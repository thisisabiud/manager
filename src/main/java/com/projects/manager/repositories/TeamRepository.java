package com.projects.manager.repositories;

import com.projects.manager.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByProjectId(Long id);

}
