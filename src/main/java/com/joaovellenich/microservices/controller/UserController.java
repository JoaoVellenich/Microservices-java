package com.joaovellenich.microservices.controller;

import com.joaovellenich.microservices.dto.user.CreateUserDTO;
import com.joaovellenich.microservices.useCases.user.*;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

    private final CreateUserUseCase createUserUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    public UserController(
            CreateUserUseCase createUserUseCase,
            GetUsersUseCase getUsersUseCase,
            GetUserByIdUseCase getUserByIdUseCase,
            UpdateUserUseCase updateUserUseCase,
            DeleteUserUseCase deleteUserUseCase
    ){
        this.createUserUseCase = createUserUseCase;
        this.getUsersUseCase = getUsersUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(CreateUserDTO user){
        try{
            logger.info("Start - createUser - " + user);
            this.createUserUseCase.createUser(user);
            return ResponseEntity.ok().build();
        }catch (Exception error){
            logger.severe("Error - createUser route - " + error.getMessage());
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
}
