package com.onlineeducationsystem.app.service;

import java.util.List;

import com.onlineeducationsystem.app.dto.LoginDto;
import com.onlineeducationsystem.app.dto.LoginResultDto;
import com.onlineeducationsystem.app.entity.Admin;

import com.onlineeducationsystem.app.exceptions.AdminException;


public interface AdminService {
	
	public Admin deleteAdmin(int id) throws AdminException;
	public Admin viewAdminById(int id) throws AdminException;
	public Admin viewAdminByEmail(String email) throws AdminException;
	public List<Admin> getAllAdminList();
	public Admin registerAdmin( Admin admin) throws AdminException;
	public Admin updateAdmin(Admin Admin, int id) throws AdminException;
	public LoginResultDto validateAdmin(LoginDto loginDto) throws AdminException;
	

}
