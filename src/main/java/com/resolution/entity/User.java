package com.resolution.entity;

import com.resolution.entity.base.AbstractEntity;
import com.resolution.infra.utils.RegexUtil;
import com.sun.istack.internal.NotNull;
import lombok.*;
import net.sf.oval.constraint.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "USERS_TABLE")
@Entity
public class User extends AbstractEntity {
    @NotNull
    @NotEmpty
    @Length(min = 1, max = 30)

    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 1, max = 30)

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @NotNull
    @NotEmpty
    @MatchPattern(pattern = RegexUtil.PHONE_PATTERN)

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @NotNull
    @NotEmpty
    @Email

    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @NotEmpty
    @MatchPattern(pattern = RegexUtil.LOGIN_PATTERN)

    @Column(name = "LOGIN", nullable = false)
    private String login;

    @NotNull
    @NotEmpty
    @MatchPattern(pattern = RegexUtil.PASSWORD_PATTERN)

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ROLE_ID", nullable = false)
    private int roleId;
}