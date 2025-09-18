package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.entity.Parent;
import com.armaan.academyapi.repository.ParentRepository;
import com.armaan.academyapi.service.ParentService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;

    @Override
    public Parent createParent(Parent parent) {
        return parentRepository.save(parent);
    }

    @Override
    public Parent getParentById(Long parentId) {
        return parentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found"));
    }

    @Override
    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    @Override
    public void deleteParent(Long parentId) {
        parentRepository.deleteById(parentId);
    }
}

