package com.example.smartdeltasystems.controller;

import com.example.smartdeltasystems.model.Student;
import com.example.smartdeltasystems.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetAllStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(new Student("1", "Иванов", "Иван", "Иванович", "Группа 1", 4.5));
        students.add(new Student("2", "Петров", "Петр", "Петрович", "Группа 2", 4.8));

        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].lastName").value("Иванов"))
                .andExpect(jsonPath("$[1].lastName").value("Петров"));

        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student("1", "Иванов", "Иван", "Иванович", "Группа 1", 4.5);

        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Иванов"));

        verify(studentService, times(1)).createStudent(any(Student.class));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student student = new Student("1", "Петров", "Петр", "Петрович", "Группа 2", 4.8);

        when(studentService.updateStudent(anyString(), any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/students/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Петров"));

        verify(studentService, times(1)).updateStudent(eq("1"), any(Student.class));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        doNothing().when(studentService).deleteStudent(anyString());

        mockMvc.perform(delete("/students/{id}", "1"))
                .andExpect(status().isNoContent());

        verify(studentService, times(1)).deleteStudent(eq("1"));
    }
}


