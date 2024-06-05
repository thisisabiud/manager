package com.projects.manager.services;

import com.projects.manager.models.DTOs.UserDTO;
import com.projects.manager.models.User;

import java.util.*;

public interface IUsersService {
    Optional<User> findOne(UUID id);
    List<User> findAll();
    boolean delete(UUID id);
    User update(UserDTO user, UUID id) throws CustomException;
    User create(UserDTO user);
}
