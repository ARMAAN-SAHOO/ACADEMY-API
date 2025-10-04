package com.armaan.academyapi.service.serviceImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.AttendanceRequestDto;
import com.armaan.academyapi.dto.response.AttendanceResponseDto;
import com.armaan.academyapi.entity.Attendance;
import com.armaan.academyapi.entity.Enrollment;
import com.armaan.academyapi.exception.ResourceNotFoundException;
import com.armaan.academyapi.mapper.AttendanceMapper;
import com.armaan.academyapi.repository.AttendanceRepository;
import com.armaan.academyapi.repository.EnrollmentRepository;
import com.armaan.academyapi.repository.StudentRepository;
import com.armaan.academyapi.repository.TimeTableRepository;
import com.armaan.academyapi.service.AttendanceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final TimeTableRepository timeTableRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    @Transactional
    public AttendanceResponseDto markAttendance(AttendanceRequestDto attendanceRequestDto) {

        Attendance attendance;

        Enrollment enrollment = enrollmentRepository.findById(attendanceRequestDto.getEnrollmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", attendanceRequestDto.getEnrollmentId()));

        Attendance existing = attendanceRepository
                .findByEnrollmentEnrollmentIdAndDate(attendanceRequestDto.getEnrollmentId(),
                        attendanceRequestDto.getDate())
                .orElse(null);

        if (existing != null) {
            // update existing bitmask
            existing.setSessionBitmask(attendanceRequestDto.getSessionBitmask());
            attendance = existing;
        } else {
            // create new one
            attendance = attendanceMapper.toEntity(attendanceRequestDto);
            attendance.setEnrollment(enrollment);
            LocalDate date = attendanceRequestDto.getDate();
            DayOfWeek day = date.getDayOfWeek();
            Long batchId = enrollment.getBatch().getBatchId();

            int totalSessions = timeTableRepository.countByBatchAndDayOfWeek(batchId, day);
            attendance.setTotalSessions(totalSessions);
        }

        Attendance saved = attendanceRepository.save(attendance);
        return attendanceMapper.toResponseDto(saved);

    }

    @Override
    public List<AttendanceResponseDto> getAttendance(Long studentId) {

        studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", studentId));

        return attendanceRepository.findAllByEnrollmentStudentStudentId(studentId).stream()
                .map(attendanceMapper::toResponseDto).toList();
    }
}
