package com.projects.manager.services;

import com.projects.manager.models.DTOs.StatsDTO;

public interface IStatsService {
    // todo: create service to fetch summary for projects number, returns on those projects, investments done, number of users, female and male

    /**
     * General project manager statistics
     * @return statistic object
     */
    StatsDTO general();
}
