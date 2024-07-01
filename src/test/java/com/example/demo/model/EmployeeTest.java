package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    @Test
    public void testEmployeeConstructorAndGetters() {
        Employee employee = new Employee(1L, "Pramila", "J", "j.pramila@example.com");

        assertEquals(1L, employee.getId());
        assertEquals("Pramila", employee.getFirstName());
        assertEquals("J", employee.getLastName());
        assertEquals("j.pramila@example.com", employee.getEmail());
    }

    @Test
    public void testEmployeeSetters() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Pramila");
        employee.setLastName("J");
        employee.setEmail("j.pramila@example.com");

        assertEquals(1L, employee.getId());
        assertEquals("Pramila", employee.getFirstName());
        assertEquals("J", employee.getLastName());
        assertEquals("j.pramila@example.com", employee.getEmail());
    }
}
