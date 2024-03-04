package com.joaovellenich.microservices.useCases.user;

import com.joaovellenich.microservices.dto.user.GetUsersDTO;
import com.joaovellenich.microservices.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class GetUserByIdUseCase {
    private final UserRepository userRepository;

    public GetUserByIdUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public GetUsersDTO getUserById(UUID id) throws Exception{
        var user = this.userRepository.findById(id).orElseThrow(()-> {
            return new Exception("User not found");
        });
        return GetUsersDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .id(user.getId())
                .build();
    }
}
