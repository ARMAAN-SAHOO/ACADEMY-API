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
@SQLDelete(sql = "UPDATE course SET deleted=true where id=?")
@SQLRestriction("deleted=false")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String name;
    private String description;

     private boolean deleted = false; 

    @OneToMany(mappedBy = "course")
    private List<CourseTeacher> courseTeachers;

    @OneToMany(mappedBy = "course")
    private List<Exam> exams;
}

