package com.projects.manager.services;

import com.projects.manager.models.DTOs.ProjectDTO;
import com.projects.manager.models.Project;
import com.projects.manager.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectsService implements IProjectService{
    @Autowired
    private ProjectRepository repository;

    @Override
    public Optional<Project> findOne(UUID id) throws CustomException {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new CustomException("Project not found")));
    }

    @Override
    public List<Project> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(UUID id) throws CustomException {
        repository.findById(id)
                .map(project -> { return true;})
                .orElseThrow(() -> new CustomException("Project not found."));
        return false;
    }

    @Override
    public Project update(ProjectDTO request, UUID id) throws CustomException {
        repository.findById(id)
                .map(project -> {
                    project.setUpdatedAt(OffsetDateTime.now());
                    project.setDescription(request.getDescription());
                    project.setAvailableBudget(request.getAvailableBudget());
                    project.setExpectedBudget(request.getExpectedBudget());
                    project.setExpectedProfit(request.getExpectedProfit());
                    project.setStatus(request.getStatus());
                    project.setTitle(request.getTitle());
                    project.setFinishingTime(request.getFinishingTime());
                    project.setStartingTime(request.getStartingTime());
                    repository.save(project);
                    return project;
                }).orElseThrow(() -> new CustomException("Project not found"));
        return null;
    }

    @Override
    public Project create(ProjectDTO request) {
        var project = Project.builder()
                .createdAt(OffsetDateTime.now())
                .availableBudget(request.getAvailableBudget())
                .description(request.getDescription())
                .updatedAt(OffsetDateTime.now())
                .expectedProfit(request.getExpectedProfit())
                .expectedBudget(request.getExpectedBudget())
                .title(request.getTitle())
                .finishingTime(request.getFinishingTime())
                .startingTime(request.getStartingTime())
                .status(request.getStatus())
                .build();
        repository.save(project);
        return project;
    }
}
