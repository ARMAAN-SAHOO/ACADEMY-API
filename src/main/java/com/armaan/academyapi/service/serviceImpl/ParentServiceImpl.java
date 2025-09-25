package com.armaan.academyapi.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.armaan.academyapi.dto.request.ParentRequestDto;
import com.armaan.academyapi.dto.response.ParentResponseDto;
import com.armaan.academyapi.entity.Parent;
import com.armaan.academyapi.mapper.ParentMapper;
import com.armaan.academyapi.repository.ParentRepository;
import com.armaan.academyapi.service.ParentService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;
    private final ParentMapper parentMapper;

    @Override
    public ParentResponseDto createParent(ParentRequestDto parentRequestDto) {
        Parent parent=parentMapper.toEntity(parentRequestDto);
        Parent savedParent=parentRepository.save(parent);
        return parentMapper.toResponseDto(savedParent);
    }

    @Override
    public ParentResponseDto getParentById(Long parentId) {
        Parent parent= parentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found"));

        return parentMapper.toResponseDto(parent);
    }

    @Override
    public List<ParentResponseDto> getAllParents() {
        return parentRepository.findAll().stream().map(parent->parentMapper.toResponseDto(parent)).toList();
    }

    @Override
    public void deleteParent(Long parentId) {
        Parent parent= parentRepository.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found"));
        parent.setDeleted(true);
    }
}

