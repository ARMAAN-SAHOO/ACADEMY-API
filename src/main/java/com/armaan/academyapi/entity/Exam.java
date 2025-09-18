package com.armaan.academyapi.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private ClassSession classSession;

    private String name;

    private String subject;

    private LocalDate date;
    
    private Integer totalMarks;

    @OneToMany(mappedBy = "exam")
    private List<Result> results;
}
