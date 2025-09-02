package dev.usmaarn.telecom.service;

import dev.usmaarn.telecom.model.User;
import dev.usmaarn.telecom.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){
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
