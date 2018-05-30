package com.resolution.entity;

import com.resolution.entity.base.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString

@Table(name = "ROLE_REF")
@Entity
public class Role extends AbstractEntity {
    @Column(name = "NAME")
    private String name;
}