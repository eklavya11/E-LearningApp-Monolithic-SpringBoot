package com.onlineeducationsystem.app.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineeducationsystem.app.entity.CourseEntity;
import com.onlineeducationsystem.app.entity.TestEntity;
import com.onlineeducationsystem.app.exceptions.CourseNotFoundException;
import com.onlineeducationsystem.app.exceptions.TestNotFoundException;
import com.onlineeducationsystem.app.repository.TestRepository;

@Service
public class TestService {

    private TestRepository testRepository;

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public void insert(TestEntity testEntity) {
        testRepository.save(testEntity);
    }

    public List<TestEntity> findAllTests() {
        return testRepository.findAll();
    }
    
    public TestEntity updateTest(TestEntity test, int id) throws TestNotFoundException {

		try {
			TestEntity result = testRepository.findById(id).get();
			if (result != null) {
				return testRepository.save(test);
			}
		} catch (NoSuchElementException e) {
			throw new TestNotFoundException("Test with this id: " + id + "not found!!!");
		}
		return null;



	}

    public Optional<Object> deleteTestById(int testId) throws TestNotFoundException {
        Optional<TestEntity> optionalTest = testRepository.findById(testId);
        if (optionalTest.isPresent()) {
            testRepository.deleteById(testId);
        } else {
            throw new TestNotFoundException("Test not found with ID: " + testId);
        }
		return null;
    }

}
