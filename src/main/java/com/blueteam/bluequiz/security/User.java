package com.blueteam.bluequiz.security;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Builder
@Document
@ToString
public class User {
    @Id
    private String _id;
    private String username;
    private String password;
    private String roles;
}

