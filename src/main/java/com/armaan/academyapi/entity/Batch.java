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
@SQLDelete(sql = "UPDATE batch SET deleted = true WHERE batch_id = ?")
@SQLRestriction("deleted=false")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;

    private String name;

     private boolean deleted = false; 

    @OneToMany(mappedBy = "batch")
    private List<Exam> exams;

    @OneToMany(mappedBy="batch")
    private List<TimeTable> timeTables;
}
