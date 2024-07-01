package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Pramila", "J", "j.pramila@example.com"),
                new Employee(2L, "Shalini", "S", "s.shalini@example.com"));

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        assertEquals("Pramila", result.get(0).getFirstName());
        assertEquals("J", result.get(0).getLastName());
        assertEquals("j.pramila@example.com", result.get(0).getEmail());
    }

    @Test
    public void testGetEmployeeById() {
        Long employeeId = 1L;
        Employee employee = new Employee(employeeId, "Pramila", "J", "j.pramila@example.com");
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeById(employeeId);

        assertEquals(employee.getFirstName(), result.get().getFirstName());
        assertEquals(employee.getLastName(), result.get().getLastName());
        assertEquals(employee.getEmail(), result.get().getEmail());
    }

    @Test
    public void testCreateEmployee() {
        Employee employeeToCreate = new Employee(2L, "Pramila", "J", "j.pramila@example.com");
        Employee createdEmployee = new Employee(1L, "Shalini", "S", "s.shalini@example.com");

        when(employeeRepository.save(any(Employee.class))).thenReturn(createdEmployee);

        Employee result = employeeService.createEmployee(employeeToCreate);

        assertEquals(createdEmployee.getId(), result.getId());
        assertEquals("Shalini", result.getFirstName());
        assertEquals("S", result.getLastName());
        assertEquals("s.shalini@example.com", result.getEmail());
    }

    @Test
    public void testUpdateEmployee() {
        Long employeeId = 1L;
        Employee updatedEmployee = new Employee(employeeId, "Pramila", "J", "j.pramila@example.com");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(new Employee()));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        Employee result = employeeService.updateEmployee(employeeId, updatedEmployee);

        assertEquals(updatedEmployee.getId(), result.getId());
        assertEquals("Pramila", result.getFirstName());
        assertEquals("J", result.getLastName());
        assertEquals("j.pramila@example.com", result.getEmail());
    }

    @Test
    public void testDeleteEmployee() {
        Long employeeId = 1L;

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }
}
