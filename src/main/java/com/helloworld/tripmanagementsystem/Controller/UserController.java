package com.helloworld.tripmanagementsystem.Controller;

import com.helloworld.tripmanagementsystem.DTO.UserDTO;
import com.helloworld.tripmanagementsystem.Entity.UserEntity;
import com.helloworld.tripmanagementsystem.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:5174 ","http://localhost:5173"})
public class UserController {

    private final UserService userService;

    public UserController(UserService authService) {
        this.userService = authService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserDTO user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity loginRequest) {
        String token = userService.authenticateUser(loginRequest.getUserName(), loginRequest.getPassword());

        if (token != null) {
            return ResponseEntity.ok(token); //  return JWT token
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
