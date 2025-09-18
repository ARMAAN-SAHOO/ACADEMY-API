package com.armaan.academyapi.service;

import java.util.List;

import com.armaan.academyapi.entity.Parent;

public interface ParentService {

    Parent createParent(Parent parent);
    Parent getParentById(Long parentId);
    List<Parent> getAllParents();
    void deleteParent(Long parentId);
}
