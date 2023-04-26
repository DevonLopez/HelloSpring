package com.example.demo.controller;


import com.example.demo.clases.Employee;
import com.example.demo.services.EmployeeServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/views/empleados")
class EmployeeController {

    private final EmployeeServiceImpl employeeIntefaceImpl;

    public EmployeeController(EmployeeServiceImpl employeeInteface) {
        this.employeeIntefaceImpl = employeeInteface;
    }


    @GetMapping("/")
    public String listarEmpleados(Model model) {
        model.addAttribute("titulo", "Lista de Empleados");
        model.addAttribute("empleados", employeeIntefaceImpl.listarTodos());
        return "views/empleados/listar";
    }

    @GetMapping("/{id}")
    public String verEmpleado(Model model, @PathVariable long id) {
        if (employeeIntefaceImpl == null)
        {
            model.addAttribute("titulo", "Empleado " + id);
            return "/views/empleados/listar";
        }
        else {
            model.addAttribute("titulo", "Empleado " + id);
            model.addAttribute("empleados", employeeIntefaceImpl.buscarPorId(id));
            return "/views/empleados/listar";
        }
    }

    @GetMapping("/add")
    public String addEmpleado( Model model) {
        model.addAttribute("formulario", new Employee());
        return "views/empleados/posts";
    }

    @PostMapping("/add/user")
    public String empleadoAgragado(@ModelAttribute("formulario") Employee.EmployeeBuilder employeeBuilder, Model model) {
            model.addAttribute("formulario", employeeBuilder);
           employeeIntefaceImpl.guardar(employeeBuilder.build());
            return "views/empleados/agregar";
    }



}
