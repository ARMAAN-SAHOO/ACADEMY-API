package com.armaan.academyapi.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Column(name = "batch_id")
    private Long batchId;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer fee;

    @Column(nullable = false)
    private Boolean deleted = false;

    @OneToMany(mappedBy = "batch")
    private List<Exam> exams = new ArrayList<>();

    @OneToMany(mappedBy = "batch")
    private List<TimeTable> timeTables = new ArrayList<>();

}
