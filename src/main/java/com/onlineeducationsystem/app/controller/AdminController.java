package com.onlineeducationsystem.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.onlineeducationsystem.app.dto.LoginDto;
import com.onlineeducationsystem.app.dto.LoginResultDto;
import com.onlineeducationsystem.app.dto.Response;
import com.onlineeducationsystem.app.entity.Admin;

import com.onlineeducationsystem.app.exceptions.AdminException;

import com.onlineeducationsystem.app.service.AdminService;



@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/registeradmin")
	@ResponseStatus(value = HttpStatus.CREATED)
	ResponseEntity<Admin> registerAdmin(@RequestBody @Valid Admin admin) throws AdminException{
		Admin result = adminService.registerAdmin(admin);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update-admin/{adminId}")
	@ResponseStatus(value = HttpStatus.CREATED)
	ResponseEntity<Admin> updateAdmin(@RequestBody @Valid Admin admin, @PathVariable int adminId)
			throws AdminException {
		Admin result = adminService.updateAdmin(admin, adminId);
		if(result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("validate/admin/login")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Response> loginAdmin(@RequestBody LoginDto LoginDto)  {

		Response response = new Response();
		try {
			LoginResultDto validateUserLogin = adminService.validateAdmin(LoginDto);
			response.setStatus(Response.SUCCESS);
			response.setStatusCode(HttpStatus.OK);
			response.setResponse(validateUserLogin);
			return new ResponseEntity<Response>(response, response.getStatusCode());

		} catch (Exception e) {
			response.setStatus(Response.FAILURE);
			response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setResponse("LOGIN FAIL");
			return new ResponseEntity<Response>(response, response.getStatusCode());
		}

	}
	
	@GetMapping("/admin/view-admin-by-id/{Id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Admin> getAdminById(@PathVariable int Id) throws AdminException{
		Admin result = adminService.viewAdminById(Id);
		if(result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAll/admins")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<List<Admin>> getAllAdminList()  {
		List<Admin> AllAdminList = adminService.getAllAdminList();
		return new ResponseEntity<>(AllAdminList, HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/delete-admin/{Id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<?> deleteAdmin(@PathVariable int Id) throws AdminException{
		Admin result = adminService.deleteAdmin(Id);
		if(result != null) {
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
