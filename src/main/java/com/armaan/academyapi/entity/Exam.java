package com.armaan.academyapi.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.armaan.academyapi.enums.ExamStatus;
import com.armaan.academyapi.enums.ExamType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    @ManyToOne
    @JoinColumn(name = "batch_id",nullable = false)
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate date;   
    
    @Column(nullable = false)
    private LocalTime startTime;  // Optional start time

    @Column(nullable = false)
    private LocalTime endTime;    // Optional end time
    
    @Column(nullable = false)
    private Integer totalMarks;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExamType type = ExamType.EXAM; // EXAM or CLASS_TEST

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExamStatus status = ExamStatus.SCHEDULED;

    @OneToMany(mappedBy = "exam")
    private List<Result> results=new ArrayList<>();
}
