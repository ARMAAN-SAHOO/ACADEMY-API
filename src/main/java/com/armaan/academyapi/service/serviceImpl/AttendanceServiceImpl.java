package com.armaan.academyapi.service.serviceImpl;

import java.time.LocalDate;
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
    public List<Attendance> getAttendanceBySession(Long sessionId) {
        return attendanceRepository.findByClassSessionId(sessionId);
    }

    @Override
    public List<Attendance> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    @Override
    public Attendance markAttendance(Long studentId, Long batchId, LocalDate date, String bitmask) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'markAttendance'");
    }

    @Override
    public Attendance getAttendance(Long studentId, LocalDate date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttendance'");
    }

    @Override
    public List<Attendance> getAttendanceForBatch(Long batchId, LocalDate date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttendanceForBatch'");
    }

    @Override
    public List<Attendance> getAttendance(Long studentId, LocalDate from, LocalDate to) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttendance'");
    }
}
