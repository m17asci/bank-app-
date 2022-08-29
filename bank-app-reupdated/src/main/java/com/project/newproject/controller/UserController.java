package com.project.newproject.controller;

import com.project.newproject.dto.UserRequestDTO;
import com.project.newproject.dto.UserResponseDTO;
import com.project.newproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private  final  UserService userService;


	@PostMapping
	public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO user) {
		return new ResponseEntity(userService.addUser(user), HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> findAllUser() {
		return ResponseEntity.ok(userService.findAllUser());

	}

	@GetMapping("{id}")
	public ResponseEntity<UserResponseDTO> getUserId(@PathVariable("tcIdentity") String tcIdentity) {
		return  ResponseEntity.ok(userService.getUser(tcIdentity));
	}

    @PutMapping("{tcIdentity}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO newUser) {
        return ResponseEntity.ok(userService.updateUser(newUser));
    }

    @DeleteMapping("{tcIdentity}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("tcIdentity") Long tcIdentity) {
        userService.deleteUserById(tcIdentity);
        return ResponseEntity.noContent().build();

    }


}
