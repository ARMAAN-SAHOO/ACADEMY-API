package com.armaan.academyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.TimeTable;

import org.springframework.stereotype.Repository;
@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable,Long>{

}
