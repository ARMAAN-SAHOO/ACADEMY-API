package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.dto.request.AttendanceRequestDto;
import com.armaan.academyapi.dto.response.AttendanceResponseDto;

public interface AttendanceService {

    AttendanceResponseDto markAttendance(AttendanceRequestDto attendanceRequestDto);
    List<AttendanceResponseDto> getAttendance(Long studentId);
   // List<Attendance> getAttendanceForBatch(Long batchId, LocalDate date);
}

