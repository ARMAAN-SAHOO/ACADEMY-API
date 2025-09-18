package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.entity.ClassSession;

public interface SessionService {
    ClassSession createSession(ClassSession session);
    ClassSession getSessionById(Long sessionId);
    List<ClassSession> getSessionsByBatch(Long batchId);
    void deleteSession(Long sessionId);
}

