package com.github.vladimirbabin.cardatabase.service;

import com.github.vladimirbabin.cardatabase.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user ->
                        User.withUsername(username)
                                .password(user.getPassword())
                                .roles(user.getRole())
                                .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
