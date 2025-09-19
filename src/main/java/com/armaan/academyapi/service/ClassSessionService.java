package com.armaan.academyapi.service;

import java.time.LocalDate;
import java.util.List;

import com.armaan.academyapi.entity.ClassSession;

public interface ClassSessionService {
    ClassSession createSession(ClassSession session);
    ClassSession getSessionById(Long sessionId);
    List<ClassSession> getSessionsByBatch(Long batchId);
    List<ClassSession> getSessionsByDate(LocalDate date);
    List<ClassSession> getSessions(Long batchId, LocalDate date);
    void deleteSession(Long sessionId);
}

