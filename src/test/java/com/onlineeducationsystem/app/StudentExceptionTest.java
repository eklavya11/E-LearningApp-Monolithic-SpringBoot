package com.onlineeducationsystem.app;

import org.junit.jupiter.api.Test;

import com.onlineeducationsystem.app.exceptions.StudentException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentExceptionTest {

    @Test
    public void testStudentExceptionWithMessage() {
        String errorMessage = "Student not found";
        StudentException exception = new StudentException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testStudentExceptionWithoutMessage() {
        StudentException exception = new StudentException();

        assertEquals(null, exception.getMessage());
    }
}
