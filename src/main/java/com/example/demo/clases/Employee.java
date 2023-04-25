package com.example.demo.clases;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;

@Entity
@Table(name="employee")
@Getter
@NoArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
@AllArgsConstructor
public final class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_id")
    public Long e_id;

    @Column(name = "nombre")
    public String nombre;

    @Column(name = "edad")
    public Long edad;


}
