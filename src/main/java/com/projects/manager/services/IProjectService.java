package com.projects.manager.services;

import com.projects.manager.models.DTOs.ProjectRequestDTO;
import com.projects.manager.models.DTOs.ProjectResponseDTO;
import com.projects.manager.models.DTOs.ProjectTeamResponseDTO;
import com.projects.manager.models.Project;

import java.util.List;

public interface IProjectService {
    /**
     * Find project using provided key
     * @param id    key used to find a project entity
     * @return  project object
     * @throws CustomException  thrown if project is absent
     */
    ProjectResponseDTO findOne(Long id) throws CustomException;

    /**
     * Find all project entities present in the database
     * @return  List of all entities; Empty list if no project in database
     */
    List<ProjectResponseDTO> findAll();

    /**
     * Remove persisted project from database
     * @param id    key used to find project entity to be removed
     * @return  true if succeed
     * @throws CustomException  thrown when no entity associated with the provided key
     */
    boolean delete(Long id) throws CustomException;

    /**
     * Update project information
     * @param request   request body containing new values
     * @param id        key to find object to whose values are to be updated
     * @return          project entity with new values
     * @throws CustomException  thrown when no entity associated with the provided key
     */
    Project update(ProjectRequestDTO request, Long id) throws CustomException;

    /**
     * Create new project entity and persist it to database
     * @param request   request body of object to be created
     * @return          created object
     */
    Project create(ProjectRequestDTO request);

    /**
     * Delete member from a project
     * @param id    member id for accessing and deleting member from database
     * @return  true if succeeded; false if failed
     */
    boolean removeMember(Long id) throws CustomException;

    /**
     * Find project using provided key
     * @param projectId     key to fetch project from database
     * @return              project with its related members
     * @throws CustomException  thrown when no entity associated with the provided key
     */
    ProjectTeamResponseDTO findProjectTeam(long projectId) throws CustomException;
}
