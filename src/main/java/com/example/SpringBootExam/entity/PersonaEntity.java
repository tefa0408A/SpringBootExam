package com.example.SpringBootExam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "persona")
@Getter
@Setter
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_persona", length = 100)
    private String name;

    @Column(name = "numero_doc",nullable = false)
    private Integer numDocument;

    @Column(name = "estado", nullable = false)
    private Integer state;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    private DireccionEntity direccionEntity;


}
