package com.onlineeducationsystem.app.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.onlineeducationsystem.app.dto.LoginDto;
import com.onlineeducationsystem.app.dto.LoginResultDto;
import com.onlineeducationsystem.app.entity.Admin;
import com.onlineeducationsystem.app.entity.Student;
import com.onlineeducationsystem.app.exceptions.AdminException;
import com.onlineeducationsystem.app.exceptions.StudentException;
import com.onlineeducationsystem.app.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
    
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin deleteAdmin(int id) throws AdminException {
		try {
			Admin result = adminRepository.findById(id).get();
			if (result != null) {
				adminRepository.delete(result);
				return result;
			}
		} catch (NoSuchElementException e) {
			throw new AdminException("Admin with admin id:" + id + " not found!!!");
		}
		return null;
	}

	@Override
	public Admin viewAdminById(int id) throws AdminException {
		try {
			Admin result = adminRepository.findById(id).get();
			if (result != null) {
				return result;
			}
		} catch (NoSuchElementException e) {
			throw new AdminException("Admin with admin id:" + id + " not found!!!");
		}
		return null;
	}

	@Override
	public Admin viewAdminByEmail(String email) throws AdminException {
		try {
			Admin result = adminRepository.findByEmail(email);
			if (result != null) {
				return result;
			}
			else {
				throw new AdminException("Admin with email:" + email + " not found!!!");
			}
		} catch (NoSuchElementException e) {
			throw new AdminException("Admin with email:" + email + " not found!!!");
		}
	}

	@Override
	public List<Admin> getAllAdminList() {
		return adminRepository.findAll();
	}

	@Override
	public Admin registerAdmin(Admin admin) throws AdminException {
		if(adminRepository.findByUsername(admin.getUsername())!=null) {
			throw new AdminException("Username already exists,enter another username");
		}


		if(adminRepository.findByEmail(admin.getEmail())!=null) {
			throw new AdminException("Email already exists,enter another email");
		}

		return adminRepository.save(admin);

	}

	@Override
	public Admin updateAdmin(Admin admin, int id) throws AdminException {
		try {
			Admin result = adminRepository.findById(id).get();
			if (result != null ) {
				return adminRepository.save(admin);
			}
		} catch (NoSuchElementException e) {
			throw new AdminException("Admin with id: " + id + " not found!!!");
		}
		return null;
	}

	@Override
	public LoginResultDto validateAdmin(LoginDto loginDto) throws AdminException {
		LoginResultDto loginResultDto = new LoginResultDto();
		Admin admin = adminRepository.findByEmail(loginDto.getEmail());
		if (admin != null) {
			if (admin.getEmail().equals(loginDto.getEmail())
					&& admin.getPassword().equals(loginDto.getPassword())) {
				loginResultDto.setId(admin.getId());
				loginResultDto.setLoginMessage("SUCCESSFULY LOGIN!!!");
				loginResultDto.setEmail(loginDto.getEmail());
				return loginResultDto;
			} else {
				throw new AdminException("Admin with username:" + loginDto.getEmail() + " not found!!!");
			}
		} else {
			loginResultDto.setLoginMessage("Invalid User");
			
		}
		return loginResultDto;
	}
}
