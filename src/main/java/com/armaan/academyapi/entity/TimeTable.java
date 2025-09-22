package com.armaan.academyapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timetableId;

     private boolean deleted = false; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id", nullable = false)
    private Batch batch;

    // Which course is being taught
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_teacher_id", nullable = false)
    private CourseTeacher courseTeacher;

    // Day of the week for recurring session (Mon, Tue, ...)
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    // Optional: start time and duration for reporting or UI
    private LocalTime startTime;  // e.g., "10:00"
    private LocalTime endTime;

    @OneToMany(mappedBy = "timetable")
    private List<ClassSession> classSessions;
}

