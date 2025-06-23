package com.telzz.sub.service.impl;

import com.telzz.sub.entity.User;
import com.telzz.sub.repository.UserRepository;
import com.telzz.sub.request.CreateUserRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder, UserRepository userRepository){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .emailAddress(request.getEmailAddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        return repository.save(user);
    }

    public User getUserByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress).orElse(null);
    }
}
