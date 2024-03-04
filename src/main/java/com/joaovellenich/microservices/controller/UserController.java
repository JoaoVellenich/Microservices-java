package com.joaovellenich.microservices.controller;

import com.joaovellenich.microservices.dto.user.CreateUserDTO;
import com.joaovellenich.microservices.dto.user.GetUsersDTO;
import com.joaovellenich.microservices.dto.user.UpdateUserDTO;
import com.joaovellenich.microservices.useCases.user.*;
import jakarta.websocket.server.PathParam;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<GetUsersDTO>> getAllUsers(){
        logger.info("Start - getAllUsers");
        var users = this.getUsersUseCase.getUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") UUID id){
        try{
            logger.info("Start - getUserById - " + id);
            var user = this.getUserByIdUseCase.getUserById(id);
            return ResponseEntity.ok().body(user);
        }catch (Exception error){
            logger.severe("Error - getUsersById - " + error.getMessage());
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") UUID id, @RequestBody CreateUserDTO user){
        try{
            logger.info("Start - updateUser - " + id + " - " + user);
            UpdateUserDTO updatedUser = UpdateUserDTO.builder()
                    .id(id)
                    .email(user.getEmail())
                    .name(user.getName())
                    .build();
            this.updateUserUseCase.updateUser(updatedUser);
            return ResponseEntity.ok().build();
        }catch (Exception error){
            logger.severe("Error - updateUser - " + error.getMessage());
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID id){
        try{
            logger.info("Start - deletingUser - " + id);
            this.deleteUserUseCase.deleteUser(id);
            return ResponseEntity.ok().build();
        }catch (Exception error){
            logger.severe("Error - deleteUser - " + error.getMessage());
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }
}
