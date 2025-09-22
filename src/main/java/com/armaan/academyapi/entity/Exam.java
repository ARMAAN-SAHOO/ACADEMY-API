package com.armaan.academyapi.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.armaan.academyapi.enums.ExamStatus;
import com.armaan.academyapi.enums.ExamType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String name;       
       private LocalDate date;       // Exam date
    private LocalTime startTime;  // Optional start time
    private LocalTime endTime;    // Optional end time
    
    private Integer totalMarks;

    @Enumerated(EnumType.STRING)
    private ExamType type = ExamType.EXAM; // EXAM or CLASS_TEST

    @Enumerated(EnumType.STRING)
    private ExamStatus status = ExamStatus.SCHEDULED;

    @OneToMany(mappedBy = "exam")
    private List<Result> results;
}
