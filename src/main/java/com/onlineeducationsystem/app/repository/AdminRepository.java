package com.onlineeducationsystem.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineeducationsystem.app.entity.Admin;
import com.onlineeducationsystem.app.entity.Student;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	Admin findByUsername(String username);
	Admin findByEmail(String email);

}
