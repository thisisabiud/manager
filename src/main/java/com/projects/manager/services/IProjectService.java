package com.projects.manager.services;

import com.projects.manager.models.DTOs.ProjectDTO;
import com.projects.manager.models.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProjectService {
    Optional<Project> findOne(UUID id) throws CustomException;
    List<Project> findAll();
    boolean delete(UUID id) throws CustomException;
    Project update(ProjectDTO request, UUID id) throws CustomException;
    Project create(ProjectDTO request);
}
