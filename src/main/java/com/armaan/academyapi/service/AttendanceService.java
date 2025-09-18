package com.armaan.academyapi.service;

import java.time.LocalDate;
import java.util.List;

import com.armaan.academyapi.entity.Attendance;

public interface AttendanceService {
    Attendance markAttendance(Long studentId, Long batchId, LocalDate date, String bitmask);
    Attendance getAttendance(Long studentId, LocalDate date);
    List<Attendance> getAttendanceForBatch(Long batchId, LocalDate date);
    List<Attendance> getAttendance(Long studentId, LocalDate from, LocalDate to);
}

