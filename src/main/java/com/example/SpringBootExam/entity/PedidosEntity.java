package com.example.SpringBootExam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
public class PedidosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;

    private String estado;

    @Column(name = "flag")
    private Integer flag=1;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "persona_id",referencedColumnName = "id",nullable = false)
    private  PersonaEntity persona;
}
