package com.joaovellenich.microservices.useCases.user;

import com.joaovellenich.microservices.dto.user.CreateUserDTO;
import com.joaovellenich.microservices.model.UserModel;
import com.joaovellenich.microservices.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateUserUseCase {
    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(CreateUserDTO userDTO){
        UserModel user = UserModel.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();
        this.userRepository.save(user);
    }
}
