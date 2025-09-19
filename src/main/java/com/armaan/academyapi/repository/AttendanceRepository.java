package com.armaan.academyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.armaan.academyapi.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long>{

    List<Attendance> findAllByEnrollmentStudentStudentId(Long studentId);

}
