package moanote.backend.service;

import lombok.RequiredArgsConstructor;
import moanote.backend.entity.User;
import moanote.backend.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // DB에서 사용자 정보를 찾아서 Spring Security의 UserDetails 객체로 변환
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(u -> org.springframework.security.core.userdetails.User
                .withUsername(u.getUsername())
                .password(u.getPassword())
                .roles("USER")
                .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}