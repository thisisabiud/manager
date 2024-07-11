/**
 * @author Abiud T Samo
 */
package com.projects.manager.services;

import com.projects.manager.models.DTOs.UserResponseDTO;
import com.projects.manager.models.DTOs.UserRequestDTO;
import com.projects.manager.models.User;

import java.util.*;

public interface IUsersService {
    /**
     * Find user using id
     * @param id    long value used to find user in database
     * @return  User object
     * @throws CustomException  if user is not present user not found message is thrown
     */
    UserResponseDTO findOne(Long id) throws CustomException;
    /**
     * Find all users in database
     * @return  list of users; empty list if no user in database
     */
    List<UserResponseDTO> findAll();
    /**
     * Remove user from database
     * @param id    key used to find user to be removed
     * @return  true if user is successfully deleted
     * @throws CustomException  if user to be deleted is absent exception is thrown
     */
    boolean delete(Long id) throws CustomException;
    /**
     * Update user information
     * @param user request body containing new values for user
     * @param id    key used to find user to be updated
     * @return      updated user entity
     * @throws CustomException  thrown if user not found
     */
    UserResponseDTO update(UserRequestDTO user, Long id) throws CustomException;
    /**
     * Create new user and persist to database
     * @param user   new user information
     * @return  created user object
     */
    UserResponseDTO create(UserRequestDTO user);
}
