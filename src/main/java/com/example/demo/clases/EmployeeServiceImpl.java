package com.example.demo.clases;

import com.example.demo.clases.Employee;
import com.example.demo.interfaces.EmployeeInteface;
import com.example.demo.interfaces.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeInteface {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> listarTodos() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public void guardar(Employee emleado) {
        employeeRepository.save(emleado);
    }

    @Override
    public Employee buscarPorId(long id) {
        return employeeRepository.findById(id).orElse(null);
    }


}
