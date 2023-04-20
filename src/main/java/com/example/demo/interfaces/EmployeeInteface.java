package com.example.demo.interfaces;

import com.example.demo.clases.Employee;

import java.util.List;

public interface EmployeeInteface {

    public List<Employee> listarTodos();
    public void guardar(Employee emleado);
    public Employee buscarPorId(long id);
}
