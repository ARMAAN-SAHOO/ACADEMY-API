package com.armaan.academyapi.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.ClassSessionRequestDto;
import com.armaan.academyapi.dto.response.ClassSessionResponseDto;
import com.armaan.academyapi.entity.ClassSession;
import com.armaan.academyapi.entity.TimeTable;
import com.armaan.academyapi.mapper.ClassSessionMapper;
import com.armaan.academyapi.repository.ClassSessionRepository;
import com.armaan.academyapi.repository.TimeTableRepository;
import com.armaan.academyapi.service.ClassSessionService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassSessionServiceImpl implements ClassSessionService {

    private final ClassSessionRepository sessionRepository;
    private final ClassSessionMapper classSessionMapper;
    private final TimeTableRepository timeTableRepository;

    @Override
    public ClassSessionResponseDto createSession(ClassSessionRequestDto classSessionRequestDto) {

        TimeTable timeTable=timeTableRepository.findById(classSessionRequestDto.getTimetableId()).orElseThrow(()->new EntityNotFoundException());

        ClassSession classSession=classSessionMapper.toEntity(classSessionRequestDto);
        classSession.setTimeTable(timeTable);
        ClassSession savedSession= sessionRepository.save(classSession);
        return classSessionMapper.toResponseDto(savedSession);
    }

    @Override
    public ClassSessionResponseDto getSessionById(Long sessionId) {
        ClassSession classSession= sessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Session not found"));
        
                return classSessionMapper.toResponseDto(classSession);
    }

    @Override
    public List<ClassSessionResponseDto> getSessionsByBatch(Long batchId) {
        return sessionRepository.findByTimeTableBatchBatchId(batchId).stream().map(classSessionMapper::toResponseDto).toList();
    }

    @Override
    public List<ClassSessionResponseDto> getSessionsByDate(LocalDate date) {
        return sessionRepository.findAllByDate(date).stream().map(classSessionMapper::toResponseDto).toList();
    }

    @Override
    public List<ClassSessionResponseDto> getSessions(Long batchId, LocalDate date) {
        return sessionRepository.findAllByTimeTableBatchBatchIdAndDate(batchId,date).stream().map(classSessionMapper::toResponseDto).toList();
    }

    @Override
    public List<ClassSessionResponseDto> getAllSessions() {
        return sessionRepository.findAll().stream().map(classSessionMapper::toResponseDto).toList();
    }
}
