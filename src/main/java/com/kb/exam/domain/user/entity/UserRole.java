package com.kb.exam.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER_ROLE")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column(name = "userSeq")
    private long userSeq;

    @Column(name = "roleCode", nullable = false)
    private String roleCode;

    public UserRole(long userSeq, String roleCode) {
        this.userSeq = userSeq;
        this.roleCode = roleCode;
    }
}
