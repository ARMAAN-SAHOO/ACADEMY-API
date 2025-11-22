package com.armaan.academyapi.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClassSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classSessionId;
    
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "timeTable_id",nullable = false)
    private TimeTable timeTable;
    
    @Column(nullable = false)
    private Integer slotIndex; 
}
