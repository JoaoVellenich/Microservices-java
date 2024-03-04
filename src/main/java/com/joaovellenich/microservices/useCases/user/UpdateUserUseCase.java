package com.joaovellenich.microservices.useCases.user;

import com.joaovellenich.microservices.dto.user.UpdateUserDTO;
import com.joaovellenich.microservices.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void updateUser(UpdateUserDTO updateUser) throws Exception{
        var user = this.userRepository.findById(updateUser.getId()).orElseThrow(() -> {
            return new Exception("User not found");
        });
        user.setEmail(updateUser.getEmail());
        user.setName(updateUser.getName());
        this.userRepository.save(user);
    }
}
