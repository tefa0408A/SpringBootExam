package com.example.SpringBootExam.service.impl;

import com.example.SpringBootExam.constants.EstadosPersonas;
import com.example.SpringBootExam.entity.DireccionEntity;
import com.example.SpringBootExam.entity.PedidosEntity;
import com.example.SpringBootExam.entity.PersonaEntity;
import com.example.SpringBootExam.repository.PedidoRepository;
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

    @Autowired
    private PedidoRepository pedidoRepository;


    @Override
    public PersonaEntity crearPersona(PersonaEntity person) {
        for (PedidosEntity pedido : person.getPedidos()) {
            pedido.setPersona(person); // Asigna la persona a cada pedido
        }
        return personaRepository.save(person);
    }

    @Override
    public List<PersonaEntity> buscarTodos() {
        return personaRepository.findByState(EstadosPersonas.ACTIVO);
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
        // Actualizar o agregar pedidos
        for (PedidosEntity pedido : person.getPedidos()) {
            if (pedido.getId() != null) {
                // Actualizar un pedido existente
                PedidosEntity pedidoExistente = pedidoRepository.findById(pedido.getId())
                        .orElseThrow(() -> new NoSuchElementException("Error: Pedido no encontrado"));
                pedidoExistente.setDescripcion(pedido.getDescripcion());
                pedidoExistente.setEstado(pedido.getEstado());
            } else {
                pedido.setPersona(personaExistente); // Asegura que el pedido tenga la referencia a la persona
                personaExistente.getPedidos().add(pedido);
            }
        }
        return personaRepository.save(personaExistente);
    }

    @Override
    public void eliminarPersona(Long id) {
        PersonaEntity personaExistente = personaRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Error Persona no encontrada"));

        personaExistente.setState(EstadosPersonas.INACTIVO);
        personaRepository.save(personaExistente);

    }
}
