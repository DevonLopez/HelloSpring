package com.example.demo.controller;


import com.example.demo.clases.Employee;
import com.example.demo.interfaces.EmployeeInteface;
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

    @Autowired
    private EmployeeInteface employeeInteface;
    @GetMapping("/")
    public String listarEmpleados(Model model) {
        List<Employee> listadoEmpleados = employeeInteface.listarTodos();
        model.addAttribute("titulo", "Lista de Empleados");
        model.addAttribute("empleados", listadoEmpleados);

        return "/views/empleados/listar";
    }

    @GetMapping("/{id}")
    public String verEmpleado(Model model, @PathVariable long id) {
        List<Employee> listadoEmpleados = new ArrayList<>();
        Employee empleadoSeleccionado = employeeInteface.buscarPorId(id);
        listadoEmpleados.add(empleadoSeleccionado);
        model.addAttribute("titulo", "Empleado " + id);
        model.addAttribute("empleados", listadoEmpleados);
        return "/views/empleados/listar";
    }

    @GetMapping("/add")
    public String addEmpleado(Model model) {
        model.addAttribute("formulario", new Employee());
        return "views/empleados/posts";
    }

    @PostMapping("/add/user")
    @Transactional
    public String empleadoAgragado(@ModelAttribute("formulario") Employee employee) {
        employeeInteface.guardar(employee);
        return "views/empleados/agregar";
    }



}
