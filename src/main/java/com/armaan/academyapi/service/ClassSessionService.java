package com.armaan.academyapi.service;

import java.time.LocalDate;
import java.util.List;

import com.armaan.academyapi.dto.request.ClassSessionRequestDto;
import com.armaan.academyapi.dto.response.ClassSessionResponseDto;

public interface ClassSessionService {
    ClassSessionResponseDto createSession(ClassSessionRequestDto session);
    ClassSessionResponseDto getSessionById(Long sessionId);
    List<ClassSessionResponseDto> getSessionsByBatch(Long batchId);
    List<ClassSessionResponseDto> getSessionsByDate(LocalDate date);
    List<ClassSessionResponseDto> getSessions(Long batchId, LocalDate date);
    List<ClassSessionResponseDto> getAllSessions();
}

