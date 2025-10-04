package com.armaan.academyapi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.armaan.academyapi.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long>{
    List<Attendance> findAllByEnrollmentStudentStudentId(Long studentId);

    Optional<Attendance> findByEnrollmentEnrollmentIdAndDate(Long enrollmentId, LocalDate date);

}
