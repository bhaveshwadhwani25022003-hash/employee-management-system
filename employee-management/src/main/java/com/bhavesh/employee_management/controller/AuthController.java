package com.bhavesh.employee_management.controller;

import com.bhavesh.employee_management.model.User;
import com.bhavesh.employee_management.repository.UserRepository;
import com.bhavesh.employee_management.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            return new ResponseEntity<>("Username taken!", HttpStatus.BAD_REQUEST);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>("Registered successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Optional<User> existing = userRepository.findByUsername(user.getUsername());
        if (existing.isPresent() &&
            passwordEncoder.matches(user.getPassword(), existing.get().getPassword())) {
            return new ResponseEntity<>(jwtUtil.generateToken(user.getUsername()), HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid credentials!", HttpStatus.UNAUTHORIZED);
    }
}