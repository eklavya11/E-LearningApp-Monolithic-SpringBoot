package com.onlineeducationsystem.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineeducationsystem.app.entity.TestEntity;
@Repository
public interface TestRepository extends JpaRepository<TestEntity, Integer>{

}
