package com.armaan.academyapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.armaan.academyapi.dto.request.ParentRequestDto;
import com.armaan.academyapi.dto.response.ParentResponseDto;
import com.armaan.academyapi.service.ParentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parents")
public class ParentController {
    private final ParentService parentService;

    @PostMapping
    public ResponseEntity<ParentResponseDto> create(@RequestBody ParentRequestDto parentRequestDto) {
        return ResponseEntity.ok(parentService.createParent(parentRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(parentService.getParentById(id));
    }

    @GetMapping
    public ResponseEntity<List<ParentResponseDto>> getAll() {
        return ResponseEntity.ok(parentService.getAllParents());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        parentService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }
}
