package com.example.demo.clases;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long e_id;
    private String nombre;
    private int edad;

    public long getE_id() {
        return e_id;
    }

    public void setE_id(long e_id) {
        this.e_id = e_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "e_id=" + e_id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
