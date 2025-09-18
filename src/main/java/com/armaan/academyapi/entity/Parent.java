package com.armaan.academyapi.entity;

import java.util.List;

import lombok.Builder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parentId;

    private String fullName;
    private String contact;
    private String relation;

    @OneToMany(mappedBy = "parent")
    private List<Student> students;
}
