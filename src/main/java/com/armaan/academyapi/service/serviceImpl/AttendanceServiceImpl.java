package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.AttendanceRequestDto;
import com.armaan.academyapi.dto.response.AttendanceResponseDto;
import com.armaan.academyapi.entity.Attendance;
import com.armaan.academyapi.mapper.AttendanceMapper;
import com.armaan.academyapi.repository.AttendanceRepository;
import com.armaan.academyapi.service.AttendanceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    public AttendanceResponseDto markAttendance(AttendanceRequestDto attendanceRequestDto) {

        Attendance attendance=attendanceMapper.toEntity(attendanceRequestDto);
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getAttendance(Long studentId) {
        return attendanceRepository.findAllByEnrollmentStudentStudentId(studentId);
    }
}
