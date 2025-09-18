package com.armaan.academyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.armaan.academyapi.entity.Parent;

import org.springframework.stereotype.Repository;
@Repository
public interface ParentRepository extends JpaRepository<Parent,Long>{

}
