package com.armaan.academyapi.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    private String fullName;
    private String contact;

     private boolean deleted = false; 

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    
    @OneToMany(mappedBy = "teacher")
    private List<CourseTeacher> courseTeachers;
}
