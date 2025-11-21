package com.armaan.academyapi.entity;

import java.util.ArrayList;
import java.util.List;

import com.armaan.academyapi.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String userName;

    private String phoneNumber;
    
    private boolean deleted=false;

    private boolean localAccountEnabled;
    private boolean passwordSet;


    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role; // STUDENT, TEACHER, ADMIN

    @OneToOne(mappedBy = "user")
    private Student student;

    @OneToOne(mappedBy = "user")
    private Teacher teacher;

    @OneToMany(mappedBy = "user")
    private List<UserAuthProvider> authProviders=new ArrayList<>();
}

