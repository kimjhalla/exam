package com.kb.exam.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER_ROLE")
public class UserRole {
    @Id
    @Column(name = "userSeq")
    private long userSeq;
    
    @Column(name = "roleCode", nullable = false)
    private String roleCode;
}
