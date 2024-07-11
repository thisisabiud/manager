/**
 * @author Abiud T Samo
 */
package com.projects.manager.services;

import com.projects.manager.models.DTOs.UserResponseDTO;
import com.projects.manager.models.DTOs.UserRequestDTO;
import com.projects.manager.models.User;
import com.projects.manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;


@Service
public class UsersService implements IUsersService {

    /**
     * Dependency injection (user DAO)
     */
    @Autowired
    private UserRepository repository;


    @Override
    public UserResponseDTO findOne(Long id) throws CustomException{
        return repository.findById(id)
                .map(user -> {
                    return new UserResponseDTO(
                            user.getId(),
                            user.getFirstname(),
                            user.getLastname(),
                            user.getAge(),
                            user.getGender(),
                            user.getBirth().toString());
                })
                .orElseThrow(() -> new CustomException("User not found"));

    }


    @Override
    public List<UserResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(user ->
                        new UserResponseDTO(
                                user.getId(),
                                user.getFirstname(),
                                user.getLastname(),
                                user.getAge(),
                                user.getGender(),
                                user.getBirth().toString()
                        )
                ).toList();
    }


    @Override
    public boolean delete(Long id) throws CustomException {
       var user = repository.findById(id)
                .orElseThrow(() -> new CustomException("User not found."));
        repository.deleteById(user.getId());
        return true;
    }


    @Override
    public UserResponseDTO update(UserRequestDTO request, Long id) throws CustomException {
        return repository.findById(id)
                .map(user -> {
                    user.setFirstname(request.getFirstname());
                    user.setLastname(request.getLastname());
                    user.setBirth(request.getBirth());
                    user.setGender(request.getGender());
                    user.setUpdatedAt(OffsetDateTime.now());
                    repository.save(user);
                    return mapUserToDTO(user);

                })
                .orElseThrow(() -> new CustomException("User not found.."));
    }


    @Override
    public UserResponseDTO create(UserRequestDTO request) {
        var user = User.builder()
                .birth(request.getBirth())
                .gender(request.getGender())
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .build();
        repository.save(user);
        return mapUserToDTO(user);
    }

    private UserResponseDTO mapUserToDTO(User user){
        return UserResponseDTO.builder()
                .age(user.getAge())
                .id(user.getId())
                .birth(user.getBirth().toString())
                .gender(user.getGender())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .build();
    }
}
