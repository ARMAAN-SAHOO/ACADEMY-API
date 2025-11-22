package com.armaan.academyapi.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.armaan.academyapi.enums.RelationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

@SQLDelete(sql = "UPDATE parent SET deleted=true where parent_id=?")
@SQLRestriction("deleted=false")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parentId;

    @Column(nullable = false)
    @Size(max = 100)
    private String fullName;

    @Column(nullable = false)
    @Pattern(regexp = "^\\+?[0-9]{10,15}$")
    private String contact;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RelationType relation;

    @Column(nullable = false)
    private boolean deleted = false;

    @OneToMany(mappedBy = "parent")
    private List<Student> students=new ArrayList<>();
}
