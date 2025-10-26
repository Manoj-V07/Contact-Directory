package com.example.contact_directory.controller;

import com.example.contact_directory.entity.User;
import com.example.contact_directory.service.UserService;
import com.example.contact_directory.mapper.EntityMapper;
import com.example.contact_directory.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        User user = EntityMapper.toUserEntity(userDTO);
        User saved = userService.createUser(user);
        return ResponseEntity.ok(EntityMapper.toUserDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getAllUsers().stream().map(EntityMapper::toUserDTO).toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(EntityMapper.toUserDTO(userService.getUserById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,@RequestBody UserDTO userDTO){
        User updated = userService.updateUser(id,EntityMapper.toUserEntity(userDTO));
        return ResponseEntity.ok(EntityMapper.toUserDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User Deleted Successfully");
    }
}
