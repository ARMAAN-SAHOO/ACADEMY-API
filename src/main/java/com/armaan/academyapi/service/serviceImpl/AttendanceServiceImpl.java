package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.AttendanceRequestDto;
import com.armaan.academyapi.dto.response.AttendanceResponseDto;
import com.armaan.academyapi.entity.Attendance;
import com.armaan.academyapi.entity.Enrollment;
import com.armaan.academyapi.mapper.AttendanceMapper;
import com.armaan.academyapi.repository.AttendanceRepository;
import com.armaan.academyapi.repository.EnrollmentRepository;
import com.armaan.academyapi.service.AttendanceService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    public AttendanceResponseDto markAttendance(AttendanceRequestDto attendanceRequestDto) {

        Enrollment enrollment=enrollmentRepository.findById(attendanceRequestDto.getEnrollmentId()).orElseThrow(()->new EntityNotFoundException());

        Attendance attendance=attendanceMapper.toEntity(attendanceRequestDto);
        attendance.setEnrollment(enrollment);
        Attendance savedAttendance= attendanceRepository.save(attendance);
        return attendanceMapper.toResponseDto(savedAttendance);
    }

    @Override
    public List<AttendanceResponseDto> getAttendance(Long studentId) {
        return attendanceRepository.findAllByEnrollmentStudentStudentId(studentId).stream().map(attendanceMapper::toResponseDto).toList();
    }
}
