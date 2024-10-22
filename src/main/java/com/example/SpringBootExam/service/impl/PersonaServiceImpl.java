package com.example.SpringBootExam.service.impl;

import com.example.SpringBootExam.entity.DireccionEntity;
import com.example.SpringBootExam.entity.PersonaEntity;
import com.example.SpringBootExam.repository.PersonaRepository;
import com.example.SpringBootExam.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;


    @Override
    public PersonaEntity crearPersona(PersonaEntity person) {
        return personaRepository.save(person);
    }

    @Override
    public List<PersonaEntity> buscarTodos() {
        return personaRepository.findByState(1);
    }

    @Override
    public PersonaEntity buscarPersonaXnumDocumento(Integer numDocument) {
        return personaRepository.findByNumDocument(numDocument);
    }

    @Override
    public PersonaEntity actualizarPersona(Long id, PersonaEntity person) {
        PersonaEntity personaExistente = personaRepository.findById(id).orElseThrow(() ->
        new NoSuchElementException("Error Persona no encontrada"));

        personaExistente.setName(person.getName());
        personaExistente.setNumDocument(person.getNumDocument());
        personaExistente.setState(person.getState());
        personaExistente.setDireccionEntity(person.getDireccionEntity());
        return personaRepository.save(personaExistente);
    }

    @Override
    public void eliminarPersona(Long id) {
        PersonaEntity personaExistente = personaRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Error Persona no encontrada"));

        personaExistente.setState(0);
        personaRepository.save(personaExistente);

    }
}
