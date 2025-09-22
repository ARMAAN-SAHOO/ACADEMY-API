package com.armaan.academyapi.entity;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

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

@SQLDelete(sql = "UPDATE parent SET deleted=true where parent_id=?")
@SQLRestriction("deleted=false")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parentId;

    private String fullName;
    private String contact;
    private String relation;

    private boolean deleted=false;

    @OneToMany(mappedBy = "parent")
    private List<Student> students;
}
