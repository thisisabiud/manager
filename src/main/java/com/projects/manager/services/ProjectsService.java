package com.projects.manager.services;

import com.projects.manager.models.DTOs.MemberDTO;
import com.projects.manager.models.DTOs.ProjectRequestDTO;
import com.projects.manager.models.DTOs.ProjectResponseDTO;
import com.projects.manager.models.DTOs.ProjectTeamResponseDTO;
import com.projects.manager.models.Project;
import com.projects.manager.models.Team;
import com.projects.manager.repositories.ProjectRepository;
import com.projects.manager.repositories.TeamRepository;
import com.projects.manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ProjectsService implements IProjectService{
    @Autowired
    private ProjectRepository repository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;


    @Override
    public ProjectResponseDTO findOne(Long id) throws CustomException {
        return repository.findById(id)
                .map(project -> ProjectResponseDTO.builder()
                        .id(project.getId())
                        .availableFund(project.getAvailableFund())
                        .requiredFund(project.getRequiredFund())
                        .expectedProfit(project.getExpectedProfit())
                        .status(project.getStatus())
                        .title(project.getTitle())
                        .to(project.getStarting())
                        .from(project.getStarting())
                        .description(project.getDescription())
                        .build()
                ).orElseThrow(() -> new CustomException("Project not found"));
    }

    @Override
    public List<ProjectResponseDTO> findAll() {

        return repository.findAll().stream()
                .map(project -> ProjectResponseDTO.builder()
                        .id(project.getId())
                        .availableFund(project.getAvailableFund())
                        .requiredFund(project.getRequiredFund())
                        .expectedProfit(project.getExpectedProfit())
                        .status(project.getStatus())
                        .title(project.getTitle())
                        .to(project.getStarting())
                        .from(project.getStarting())
                        .description(project.getDescription())
                        .build()).toList();
    }

    @Override
    public ProjectTeamResponseDTO findProjectTeam(long projectId) throws CustomException {
       var entity = this.findOne(projectId);
       var members = teamRepository.findByProjectId(projectId).stream()
               .map(team -> MemberDTO.builder()
                       .memberId(team.getMember().getId())
                       .position(team.getPosition())
                       .build()
               ).toList();
       return ProjectTeamResponseDTO.builder()
               .project(entity)
               .team(members)
               .build();
    }

    @Override
    public boolean delete(Long id) throws CustomException {
        var response = repository.findById(id)
                .orElseThrow(() -> new CustomException("Project not found."));
        if (response != null) {
            repository.delete(response);
            return true;
        }
        return false;
    }

    @Override
    public Project update(ProjectRequestDTO request, Long id) throws CustomException {
        /*
          Find existing project using provided key or throw exception if project is not found
         */
        var project = repository.findById(id)
                .orElseThrow(() -> new CustomException("Project not found"));

        /*
          Update the existing project
         */
        project.setUpdatedAt(OffsetDateTime.now());
        project.setDescription(request.getDescription());
        project.setAvailableFund(request.getAvailableFund());
        project.setRequiredFund(request.getRequiredFund());
        project.setExpectedProfit(request.getExpectedProfit());
        project.setStatus(request.getStatus());
        project.setTitle(request.getTitle());
        project.setStarting(request.getFrom());
        project.setFinishing(request.getTo());
        repository.save(project); // persist new values on database

        updateTeamMembers(request, id, project);

        return project;
    }

    private void updateTeamMembers(ProjectRequestDTO request, Long id, Project project) {
    /*
      Get a list of project members using the same key used to find the project
     */
        List<Team> existingTeamList = teamRepository.findByProjectId(project.getId())
                                                    .stream()
                                                    .toList();
        if (!request.getMembers().isEmpty()) {
            for (var requestMember : request.getMembers()) {
                boolean foundExisting = false;
                for (Team existingMember : existingTeamList) {
                    if (existingMember.getMember().getId().equals(requestMember.getMemberId())) {
                        existingMember.setPosition(requestMember.getPosition());
                        existingMember.setUpdatedAt(OffsetDateTime.now());
                        teamRepository.save(existingMember);
                        foundExisting = true;
                        break;
                    }
                }

                if (!foundExisting) {
                    Team team = Team.builder()
                            .position(requestMember.getPosition())
                            .project(project)
                            .member(userRepository.findById(requestMember.getMemberId()).orElseThrow())
                            .createdAt(OffsetDateTime.now())
                            .updatedAt(OffsetDateTime.now())
                            .build();
                    teamRepository.save(team);
                }
            }
        }
    }

    @Override
    public Project create(ProjectRequestDTO request) {
        var project = Project.builder()
                .createdAt(OffsetDateTime.now())
                .availableFund(request.getAvailableFund())
                .description(request.getDescription())
                .updatedAt(OffsetDateTime.now())
                .expectedProfit(request.getExpectedProfit())
                .requiredFund(request.getRequiredFund())
                .title(request.getTitle())
                .starting(request.getTo())
                .finishing(request.getFrom())
                .status(request.getStatus())
                .build();
        repository.save(project);
        if (!request.getMembers().isEmpty()) {
            for (var member: request.getMembers()) {

                   Team team = Team.builder()
                            .position(member.getPosition())
                            .project(project)
                            .member(userRepository.findById(member.getMemberId()).orElseThrow())
                            .createdAt(OffsetDateTime.now())
                            .updatedAt(OffsetDateTime.now())
                            .build();
                teamRepository.save(team);
            }
        }
        return project;
    }

    /**
     * Delete member from a project
     *
     * @param id member id for accessing and deleting member from database
     * @return true if succeeded; false if failed
     */
    @Override
    public boolean removeMember(Long id) throws CustomException {
        teamRepository.findById(id)
                .orElseThrow(() -> new CustomException("Member with " + id + " does not exist"));
        teamRepository.deleteById(id);
        return true;
    }

}
