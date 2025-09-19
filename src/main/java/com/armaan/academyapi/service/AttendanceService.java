package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.entity.Attendance;

public interface AttendanceService {

    Attendance markAttendance(Attendance attendance);
    List<Attendance> getAttendance(Long studentId);
   // List<Attendance> getAttendanceForBatch(Long batchId, LocalDate date);
}

