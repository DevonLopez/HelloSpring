package com.example.demo.services;

import com.example.demo.clases.Employee;
import com.example.demo.interfaces.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class EmployeeServiceImplTest {


    EmployeeServiceImpl employeeService;

    @Mock
    EmployeeRepository employeeRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    @DisplayName("Listar Todos empleados")
    void listarTodos() {
        when(employeeRepository.findAll()).thenReturn(List.of());

        List<Employee> employees = employeeService.listarTodos();
        assertAll("valid Value",
                () -> assertNotNull(employees),
                () -> assertEquals(0, employees.size())
        );
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Guardar empleado any")
    void guardar() {
        employeeService.guardar(any());
        verify(employeeRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Guardar empleado y comprobar que recibe el elemento")
    void guardarEmpleado() {
        Employee employee = Employee.builder().setE_id(96L).setNombre("Camilo").setEdad(15L).build();
        employeeService.guardar(employee);
        verify(employeeRepository, times(1)).save(employee);
        verify(employeeRepository).save(argThat(argument -> argument.equals(employee)));
    }

    @Test
    @DisplayName("Guardar empleado ejemplo all parametros")
    void guardarEmpleadoTodosParametros() {
        Employee employee = Employee.builder()
                .setE_id(20L)
                .setNombre("Devon")
                .setEdad(21L).build();
        employeeService.guardar(employee);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    @DisplayName("Guardar empleado ejemplo sin id")
    void guardarEmpleadoNoId() {
        Employee employee = Employee.builder()
                .setNombre("Devon")
                .setEdad(21L).build();
        employeeService.guardar(employee);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    @DisplayName("Guardar empleado ejemplo sin nombre ni id")
    void guardarEmpleadoNoIdNoNombre() {
        Employee employee = Employee.builder()
                .setEdad(21L).build();
        employeeService.guardar(employee);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    @DisplayName("Guardar empleado solo id")
    void guardarEmpleadoSoloId() {
        Employee employee = Employee.builder()
                .setE_id(21L).build();
        employeeService.guardar(employee);
        verify(employeeRepository, times(1)).save(employee);
    }



    @Test
    @DisplayName("Buscar empleado pasando un identificador")
    void buscarPorId() {
        when(employeeRepository.findById(any())).thenReturn(Optional.of(Employee.builder().setE_id(45L).build()));
        Optional<Employee> employeeOptional = Optional.ofNullable(employeeService.buscarPorId(45L));
        assertNotNull(employeeOptional);
        assertTrue(employeeOptional.isPresent());
        assertEquals(45L, employeeOptional.get().getE_id());
        verify(employeeRepository, times(1)).findById(45L);
    }
}