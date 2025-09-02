package dev.usmaarn.telecom.service;

import dev.usmaarn.telecom.model.User;
import dev.usmaarn.telecom.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void createUserShouldCreateUserSuccessfully(){
        User user = User.builder()
                .build();
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User createdUser = userService.createUser(user);
    }
}
