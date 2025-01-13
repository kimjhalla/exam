package com.kb.exam.domain.user.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kb.exam.domain.user.entity.User;
import com.kb.exam.domain.user.entity.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVO implements UserDetails {
    private long seq;
    private String name;
    private String email;
    private String nickname;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;

    private List<UserRole> roles;

    public UserVO(User user, List<UserRole> roles) {
        this.seq = user.getSeq();
        this.name = user.getName();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.registerDate = user.getRegisterDate();
        this.updateDate = user.getUpdateDate();
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleCode())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.name;
    }
}
