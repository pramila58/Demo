package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Pramila", "J", "j.pramila@example.com"),
                new Employee(2L, "Shalini", "S", "s.shalini@example.com"));

        when(employeeService.getAllEmployees()).thenReturn(employees);

        List<Employee> result = employeeController.getAllEmployees();

        assertEquals(2, result.size());
        assertEquals("Pramila", result.get(0).getFirstName());
        assertEquals("J", result.get(0).getLastName());
        assertEquals("j.pramila@example.com", result.get(0).getEmail());
    }

    @Test
    public void testGetEmployeeById() {
        Long employeeId = 1L;
        Employee employee = new Employee(employeeId, "Pramila", "J", "j.pramila@example.com");
        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.of(employee));

        ResponseEntity<Employee> response = employeeController.getEmployeeById(employeeId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pramila", response.getBody().getFirstName());
        assertEquals("J", response.getBody().getLastName());
        assertEquals("j.pramila@example.com", response.getBody().getEmail());
    }

    @Test
    public void testCreateEmployee() {
        Employee employeeToCreate = new Employee(2L, "Pramila", "J", "j.pramila@example.com");
        Employee createdEmployee = new Employee(1L, "Shalini", "S", "s.shalini@example.com");

        when(employeeService.createEmployee(any(Employee.class))).thenReturn(createdEmployee);

        Employee result = employeeController.createEmployee(employeeToCreate);

        assertEquals(createdEmployee.getId(), result.getId());
        assertEquals("Shalini", result.getFirstName());
        assertEquals("S", result.getLastName());
        assertEquals("s.shalini@example.com", result.getEmail());
    }

    @Test
    public void testUpdateEmployee() {
        Long employeeId = 1L;
        Employee updatedEmployee = new Employee(employeeId, "Pramila", "J", "j.pramila@example.com");

        when(employeeService.updateEmployee(eq(employeeId), any(Employee.class))).thenReturn(updatedEmployee);

        ResponseEntity<Employee> response = employeeController.updateEmployee(employeeId, updatedEmployee);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pramila", response.getBody().getFirstName());
        assertEquals("J", response.getBody().getLastName());
        assertEquals("j.pramila@example.com", response.getBody().getEmail());
    }

    @Test
    public void testDeleteEmployee() {
        Long employeeId = 1L;

        ResponseEntity<Void> response = employeeController.deleteEmployee(employeeId);

        verify(employeeService, times(1)).deleteEmployee(employeeId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
