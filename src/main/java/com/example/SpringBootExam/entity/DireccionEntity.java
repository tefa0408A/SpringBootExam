package com.example.SpringBootExam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "direccion")
@Getter
@Setter
public class DireccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calle", length = 100)
    private String calle;

    private String ciudad;

}
