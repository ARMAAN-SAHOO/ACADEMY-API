package com.armaan.academyapi.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.armaan.academyapi.enums.Gender;
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
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dateOfBirth;

    private String address;

    private boolean deleted = false;

    private boolean localAccountEnabled;
    private boolean passwordSet;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    @OneToOne(mappedBy = "user")
    private Student student;

    @OneToOne(mappedBy = "user")
    private Teacher teacher;

    @OneToMany(mappedBy = "user")
    private List<UserAuthProvider> authProviders = new ArrayList<>();
}
