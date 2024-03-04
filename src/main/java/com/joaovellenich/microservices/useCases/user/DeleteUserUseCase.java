package com.joaovellenich.microservices.useCases.user;

import com.joaovellenich.microservices.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class DeleteUserUseCase {
    private final UserRepository userRepository;

    public DeleteUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void deleteUser(UUID id) throws Exception{
        var user = this.userRepository.findById(id).orElseThrow(() -> {
            return new Exception("User not found");
        });
        this.userRepository.delete(user);
    }
}
