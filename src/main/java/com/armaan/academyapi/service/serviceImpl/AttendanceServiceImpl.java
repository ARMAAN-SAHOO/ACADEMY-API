package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.Attendance;
import com.armaan.academyapi.repository.AttendanceRepository;
import com.armaan.academyapi.service.AttendanceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceRepository attendanceRepository;

    @Override
    public Attendance markAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getAttendance(Long studentId) {
        return attendanceRepository.findAllByEnrollmentStudentStudentId(studentId);
    }
}
