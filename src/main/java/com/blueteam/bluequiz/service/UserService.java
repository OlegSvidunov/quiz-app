package com.blueteam.bluequiz.service;

import com.blueteam.bluequiz.persistence.UserRepository;
import com.blueteam.bluequiz.security.User;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserService(UserRepository userRepository,
                       MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void init() {
        try {
            saveNewUser("admin", "admin", "ADMIN");
        } catch (IllegalStateException e) {
            //ignore - temp mock only for test purposes
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRoles()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public boolean saveNewUser(String username, String password, String role) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("User can't have empty name or password!");
        }
        if (hasUserUniqueName(username)) {
            userRepository.save(User.builder()
                    .username(username)
                    .password(new BCryptPasswordEncoder().encode(password))
                    .roles(role)
                    .build());
            return true;
        }
        return false;
    }

    private boolean hasUserUniqueName(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        List<User> users = mongoTemplate.find(query, User.class);
        return users.isEmpty();
    }

    public String getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
}

