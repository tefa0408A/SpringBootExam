package com.example.SpringBootExam.service;

import com.example.SpringBootExam.entity.PersonaEntity;

import java.util.List;

public interface PersonaService {

    PersonaEntity crearPersona(PersonaEntity person);
    List<PersonaEntity> buscarTodos();
    PersonaEntity buscarPersonaXnumDocumento(Integer numDocument);
    PersonaEntity actualizarPersona(Long id, PersonaEntity person);
    void eliminarPersona(Long id);
}
