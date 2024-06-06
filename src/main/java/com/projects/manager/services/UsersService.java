package com.projects.manager.services;

import com.projects.manager.models.DTOs.UserDTO;
import com.projects.manager.models.User;
import com.projects.manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UsersService implements IUsersService {

    @Autowired
    private UserRepository repository;

    @Override
    public Optional<User> findOne(UUID id) throws CustomException{
        var user = Optional.ofNullable(repository.findById(id).orElseThrow(() -> new CustomException("User not found")));
        return user;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean delete(UUID id) throws CustomException {
        var user = repository.findById(id).orElseThrow(() -> new CustomException("User not found."));
        repository.deleteById(id);
        return true;
    }

    @Override
    public User update(UserDTO request, UUID id) throws CustomException {
        return repository.findById(id)
                .map(user -> {
                    user.setFirstname(request.getFirstname());
                    user.setLastname(request.getLastname());
                    user.setBirth(request.getBirth());
                    user.setGender(request.getGender());
                    user.setUpdatedAt(OffsetDateTime.now());
                    repository.save(user);
                    return user;
                })
                .orElseThrow(() -> new CustomException("User not found.."));
    }

    @Override
    public User create(UserDTO request) {
        var user = User.builder()
                .birth(request.getBirth())
                .gender(request.getGender())
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .build();
        repository.save(user);
        return user;
    }
}
