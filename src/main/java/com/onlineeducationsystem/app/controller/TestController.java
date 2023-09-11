package com.onlineeducationsystem.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import com.onlineeducationsystem.app.entity.CourseEntity;
import com.onlineeducationsystem.app.entity.TestEntity;
import com.onlineeducationsystem.app.exceptions.CourseNotFoundException;
import com.onlineeducationsystem.app.exceptions.TestNotFoundException;
import com.onlineeducationsystem.app.logger.LoggerUtil;
import com.onlineeducationsystem.app.service.TestService;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = { "*" })
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

	@Autowired
	private TestService testService;

	@PostMapping("/test/add")
	public ResponseEntity<Map<String, String>> addTest(@RequestBody TestEntity testEntity) {
		testService.insert(testEntity);
		LoggerUtil.logInfo("Test details are posted");
		
		Map<String, String> response = new HashMap<>();
	    response.put("message", "Test Added");
	    
	    ResponseEntity<Map<String, String>> responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
	    return responseEntity;
	    
	}

	@GetMapping("/test/all")
	public List<TestEntity> getAllTests() throws TestNotFoundException {
		List<TestEntity> tests = testService.findAllTests();
		return tests;
	}
	
	@PutMapping("/test/update/{tid}")
	public ResponseEntity<TestEntity> updateTest(@RequestBody TestEntity test,@PathVariable int tid)

	throws TestNotFoundException {

		TestEntity result = testService.updateTest(test, tid);
		if (result != null) {
			LoggerUtil.logInfo("test details are updated");
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}



	 
	}

	@DeleteMapping("/test/delete/{id}")
	public ResponseEntity<Object> deleteTestById(@PathVariable("id") int testId) throws TestNotFoundException {
		Optional<Object> optional = testService.deleteTestById(testId);

		if (!optional.isPresent()) {
			LoggerUtil.logInfo("Test details are deleted");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Test with test id:" + testId + " not found!!!");

		}

		Object test = optional.get();

		return ResponseEntity.status(HttpStatus.OK).body(test);

	}
}
