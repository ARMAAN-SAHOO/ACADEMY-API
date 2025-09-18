package com.armaan.academyapi.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.ClassSession;
import com.armaan.academyapi.repository.ClassSessionRepository;
import com.armaan.academyapi.service.ClassSessionService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassSessionServiceImpl implements ClassSessionService {
    private final ClassSessionRepository sessionRepository;

    @Override
    public ClassSession createSession(ClassSession session) {
        return sessionRepository.save(session);
    }

    @Override
    public ClassSession getSessionById(Long sessionId) {
        return sessionRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Session not found"));
    }

    @Override
    public List<ClassSession> getSessionsByBatch(Long batchId) {
        return sessionRepository.findByBatchId(batchId);
    }

    @Override
    public void deleteSession(Long sessionId) {
        sessionRepository.deleteById(sessionId);
    }

    @Override
    public List<ClassSession> getSessionsByDate(LocalDate date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSessionsByDate'");
    }
}
