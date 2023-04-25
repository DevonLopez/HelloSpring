package com.example.demo.services;

import com.example.demo.clases.Employee;
import com.example.demo.interfaces.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> listarTodos() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public void guardar(Employee empleado) {
        employeeRepository.save(empleado);
    }

    public Employee buscarPorId(long id) {
        return employeeRepository.findById(id).orElse(null);
    }


}
