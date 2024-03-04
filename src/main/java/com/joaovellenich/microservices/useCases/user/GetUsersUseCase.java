package com.joaovellenich.microservices.useCases.user;

import com.joaovellenich.microservices.dto.user.GetUsersDTO;
import com.joaovellenich.microservices.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetUsersUseCase {
    private final UserRepository userRepository;

    public GetUsersUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<GetUsersDTO> getUsers(){
        var users = this.userRepository.findAll();
        return users.stream().map(user -> GetUsersDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .id(user.getId())
                .build()
        ).collect(Collectors.toList());
    }
}
