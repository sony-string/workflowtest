package moanote.backend.security;

import moanote.backend.repository.UserRepository;
import moanote.backend.entity.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username으로 Repository에서 user을 찾는 method
        User user = repo.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("계정을 찾을 수 없습니다.");
        }

        return new CustomUserDetails(user);
    }
}
