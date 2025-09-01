package dev.usmaarn.telecom.service;

import dev.usmaarn.telecom.dto.request.CreateAccountDto;
import dev.usmaarn.telecom.model.User;
import dev.usmaarn.telecom.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User createUser(CreateAccountDto dto){
        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber("+234".concat(dto.getPhoneNumber()))
                .password(passwordEncoder.encode(dto.getPassword()))
                .referrer(findByReferrerId(dto.getReferrerId()))
                .build();
        return userRepository.save(user);
    }

    public User findByReferrerId(String referrerId){
        return userRepository.findByEmailOrPhoneNumber(referrerId, referrerId).orElse(null);
    }
    
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean existsByPhoneNumber(String phoneNumber){
        return userRepository.existsByPhoneNumber(phoneNumber);
    }
}
