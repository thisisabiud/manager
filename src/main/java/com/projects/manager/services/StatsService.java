package com.projects.manager.services;

import com.projects.manager.models.DTOs.ProjectResponseDTO;
import com.projects.manager.models.DTOs.StatsDTO;

import com.projects.manager.models.enums.Gender;
import com.projects.manager.models.enums.ProjectStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService implements IStatsService{

    @Autowired
    private ProjectsService projectsService;

    @Autowired
    private UsersService usersService;
    /**
     * General project manager statistics
     *
     * @return statistic object
     */
    @Override
    public StatsDTO general() {

//        private double expectedReturn;
//        private double investment;
//        private int projects;
//        private int completed;
//        private int pending;
//        private int ongoing;
//        private int female;
//        private int male;
//        private int users;

        var projects = projectsService.findAll();
        var users = usersService.findAll();

        double returns = projects.stream().mapToDouble(ProjectResponseDTO::getExpectedProfit).sum();
        double requiredFund = projects.stream().mapToDouble(ProjectResponseDTO::getRequiredFund).sum();
        double availableFund = projects.stream().mapToDouble(ProjectResponseDTO::getAvailableFund).sum();
        long projectCount = projects.size();
        long completed = projects.stream().filter(p -> p.getStatus() == ProjectStatus.COMPLETED).count();
        long pending = projects.stream().filter(p -> p.getStatus() == ProjectStatus.PENDING).count();
        long ongoing = projectCount - (completed + pending);
        long female = users.stream().filter(u -> u.getGender() == Gender.FEMALE).count();
        long userCount = users.size();
        long male = userCount - female;

        return StatsDTO.builder()
                .expectedReturn(returns)
                .male(male)
                .female(female)
                .completed(completed)
                .ongoing(ongoing)
                .pending(pending)
                .requiredFund(requiredFund)
                .availableFund(availableFund)
                .projects(projectCount)
                .users(userCount)
                .build();
    }
}
