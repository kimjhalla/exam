package com.kb.exam.domain.user.entity;

import com.kb.exam.domain.user.vo.UserJoinVO;
import com.kb.exam.util.Utils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, updatable = false)
    private LocalDateTime registerDate;

    @Column(nullable = false)
    private LocalDateTime updateDate;

    public User(UserJoinVO vo) {
        this.email = vo.email();
        this.password = Utils.hashPassword(vo.password());
        this.name = vo.name();
        this.nickname = vo.nickname();
        LocalDateTime now = LocalDateTime.now();
        this.registerDate = now;
        this.updateDate = now;
    }

}
