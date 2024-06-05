package com.projects.manager.repositories;

import com.projects.manager.models.DTOs.UserDTO;
import com.projects.manager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
