package com.blueteam.bluequiz.service;

import com.blueteam.bluequiz.persistence.UserRepository;
import com.blueteam.bluequiz.security.User;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
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

@Log4j
@Service
public class QuizUserService implements UserDetailsService {


    final UserRepository repository;
    final MongoTemplate mongoTemplate;

    @Autowired
    public QuizUserService(UserRepository repository,
                           MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    @PostConstruct
    public void init() {
        try {
            saveNewUser("user", "user", "USER");
            saveNewUser("admin", "admin", "ADMIN");
        } catch (IllegalStateException e) {
            //ignore - mock only for test purposes
        }
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

    public void deleteUserById(String id) {
        repository.deleteById(id);
    }

    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public void saveNewUser(String username, String password, String role) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("User can't have empty name or password!");
        }
        if (hasUserUniqueName(username)) {
            repository.save(User.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .roles(role)
                .build());
        } else {
            throw new IllegalStateException(String.format("User with name %s already exists", username));
        }
    }

    private boolean hasUserUniqueName(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        List<User> users = mongoTemplate.find(query, User.class);
        return users.isEmpty();
    }
}

