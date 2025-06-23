package com.telzz.sub.service.impl;

import com.telzz.sub.entity.User;
import com.telzz.sub.entity.UserDetailsImpl;
import com.telzz.sub.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(username).orElseThrow(
                () -> new UsernameNotFoundException("user not found")
        );
        return new UserDetailsImpl(user);
    }
}
