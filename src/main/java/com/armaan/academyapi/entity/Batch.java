package com.armaan.academyapi.entity;

import java.util.List;
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
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;

    private String name;

     private boolean deleted = false; 

    @OneToMany(mappedBy = "batch")
    private List<ClassSession> sessions;

    @OneToMany(mappedBy = "batch")
    private List<Exam> exams;
}
