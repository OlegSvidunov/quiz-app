package com.blueteam.bluequiz.service;

import com.blueteam.bluequiz.persistence.UserRepository;
import com.blueteam.bluequiz.security.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class QuizUserService implements UserDetailsService {
    final UserRepository repository;

    public QuizUserService(UserRepository repository) {
        this.repository = repository;
    }

    private static List<User> quizUserList = Arrays.asList(User.builder()
                    .id(1)
                    .username("admin")
                    .password("admin")
                    .roles("ADMIN")
                    .build(),
            User.builder()
                    .id(2)
                    .username("user")
                    .password("user")
                    .roles("USER")
                    .build()
    );

    @PostConstruct
    public void init() {
        repository.saveAll(quizUserList);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRoles()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}

