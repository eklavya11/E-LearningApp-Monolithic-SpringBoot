package com.onlineeducationsystem.app.controller;

 

import java.util.List;

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
import com.onlineeducationsystem.app.exceptions.CourseNotFoundException;

import com.onlineeducationsystem.app.logger.LoggerUtil;

import com.onlineeducationsystem.app.service.CourseService;

 

@RestController

@RequestMapping("/api")

@CrossOrigin(origins = { "*" })

public class CourseController {

 

@Autowired

private CourseService courseService;

 

@PostMapping("/course/add")

public ResponseEntity<CourseEntity> addCourse(@RequestBody CourseEntity courseEntity) {

CourseEntity result = courseService.insert(courseEntity);

 



if (result != null) {
	LoggerUtil.logInfo("course details are posted");
	return new ResponseEntity<>(result, HttpStatus.OK);
} else {
	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
}

}


@PostMapping("/add-course-to-student/{id}")
public CourseEntity addCourseToStudent(@PathVariable int id, @RequestBody CourseEntity course)
		throws CourseNotFoundException {

	return this.courseService.addCourseToStudentById(id, course);
}

@DeleteMapping("/delete-course-from-student/{id}")
public CourseEntity deleteCourseFromStudent(@PathVariable int id, @RequestBody CourseEntity course)
		throws CourseNotFoundException {

	return this.courseService.deleteCourseFromStudentById(id, course);
}

 

@GetMapping("/course/all")

public List<CourseEntity> getAllCourses() throws CourseNotFoundException {

List<CourseEntity> courses = courseService.findAllCourses();

LoggerUtil.logInfo("All course details are viewed");

return courses;

}

@GetMapping("/get-course-by-student/{id}")
public List<CourseEntity> getAllStudentCourses(@PathVariable int id)throws CourseNotFoundException{
	List<CourseEntity> courses = courseService.getCoursesByStudent(id);
	return courses;
}

 

@DeleteMapping("/course/delete/{id}")

 

public ResponseEntity<Object> deleteCourseById(@PathVariable("id") int courseId) throws CourseNotFoundException {

 

Optional<Object> optional = courseService.deletecourseById(courseId);

 

if (!optional.isPresent()) {

 

LoggerUtil.logInfo("course details are deleted");

 

return ResponseEntity.status(HttpStatus.BAD_REQUEST)

.body("Course with course id:" + courseId + " not found!!!");

 

}

 

Object course = optional.get();

 

return ResponseEntity.status(HttpStatus.OK).body(course);

 

}

 

@GetMapping("/one/{id}")

 

public ResponseEntity<Object> getCourseById(@PathVariable("id") int id) throws CourseNotFoundException {

 

Optional<CourseEntity> optional = courseService.getCourseById(id);

 

if (!optional.isPresent()) {

LoggerUtil.logInfo("course details are not found");

 

return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID Given");

 

}

 

CourseEntity course = optional.get();

 

return ResponseEntity.status(HttpStatus.OK).body(course);

 

}

 

@PutMapping("/course/update/{cid}")
public ResponseEntity<CourseEntity> updateCourse(@RequestBody CourseEntity course,@PathVariable int cid)

throws CourseNotFoundException {

	CourseEntity result = courseService.updateCourse(course, cid);
	if (result != null) {
		LoggerUtil.logInfo("course details are updated");
		return new ResponseEntity<>(result, HttpStatus.OK);
	} else {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}



 
}

 

 

 

}