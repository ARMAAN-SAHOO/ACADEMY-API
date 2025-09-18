package com.armaan.academyapi.entity;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;

    private String name;

    @OneToMany(mappedBy = "batch")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "batch")
    private List<ClassSession> sessions;

    @OneToMany(mappedBy = "batch")
    private List<Exam> exams;

    @OneToMany
    private List<Attendance> attendances;
}
