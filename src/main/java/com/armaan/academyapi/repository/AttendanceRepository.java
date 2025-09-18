package com.armaan.academyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance,Long>{

}
