package com.kb.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long seq;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    private List<UserRole> roles;
}
