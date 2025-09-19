package com.armaan.academyapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armaan.academyapi.entity.ClassSession;
import com.armaan.academyapi.service.ClassSessionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sessions")
public class ClassSessionController {
    private final ClassSessionService classSessionService;

    @PostMapping
    public ResponseEntity<ClassSession> create(@RequestBody ClassSession session) {
        return ResponseEntity.ok(classSessionService.createSession(session));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassSession> get(@PathVariable Long id) {
        return ResponseEntity.ok(classSessionService.getSessionById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassSession>> getAll() {
        return ResponseEntity.ok(classSessionService.getAllSessions());
    }
}
