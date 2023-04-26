package com.example.demo.controller;

import com.example.demo.clases.Employee;
import com.example.demo.services.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {


    EmployeeController employeeController;

    @Mock
    EmployeeServiceImpl employeeService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeController = new EmployeeController(employeeService);
    }

    @Test
    @DisplayName("Lista de empleados controller")
    void listarEmpleados() {
        when(employeeService.listarTodos()).thenReturn(List.of());
        String view = employeeController.listarEmpleados(model);
        assertEquals("views/empleados/listar", view);
        verify(model, times(2)).addAttribute(any(), any());
        verify(employeeService, times(1)).listarTodos();
    }

    @Test
    @DisplayName("Añadir empleado en el formulario")
    void addEmpleado() {
        String view = employeeController.addEmpleado(model);
        assertEquals("views/empleados/posts", view);
        verify(model, times(1)).addAttribute(any(),any());
    }

    @Test
    @DisplayName("Agregar el usuario con guardar() y pasar el modelo")
    void empleadoAgragado() {

        String view = employeeController.empleadoAgragado(Employee.builder().setE_id(1L).setNombre("Devon").setEdad(53L), model);
        assertEquals("views/empleados/agregar", view);
        verify(employeeService, times(1)).guardar(any());
        verify(model, times(1)).addAttribute(any(),any());
    }

    @Test
    @DisplayName("Controlador usuario por id")
    void verEmpleado() {
        String view = employeeController.verEmpleado(model, 1L);
        assertEquals("/views/empleados/listar", view);
        verify(model, times(2)).addAttribute(any(), any());
        verify(employeeService, times(1)).buscarPorId(1L);
    }

}