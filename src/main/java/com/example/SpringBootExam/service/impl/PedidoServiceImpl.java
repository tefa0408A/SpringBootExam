package com.example.SpringBootExam.service.impl;

import com.example.SpringBootExam.entity.PedidosEntity;
import com.example.SpringBootExam.entity.PersonaEntity;
import com.example.SpringBootExam.repository.PedidoRepository;
import com.example.SpringBootExam.repository.PersonaRepository;
import com.example.SpringBootExam.service.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public PedidosEntity crearPedido(Long personaId, PedidosEntity pedido) {
        PersonaEntity personaExistente = personaRepository.findById(personaId).orElseThrow(() -> new NoSuchElementException("Error Persona no existe"));
        pedido.setPersona(personaExistente);
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<PedidosEntity> obtenerTodos(String estado) {
        return pedidoRepository.findByEstadoAndFlag(estado,1);
        }

    @Override
    public List<PedidosEntity> buscarPedidoPorParametro(Long id, String estado) {
        List<PedidosEntity> resultados = new ArrayList<>();

        if (id != null) {
            PedidosEntity pedido = pedidoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Error: Pedido no existe"));
            if (pedido.getFlag() == 1) { // Asegúrate de que el pedido tenga flag igual a 1
                resultados.add(pedido); // Agrega el pedido encontrado a la lista
            }
        } else if (estado != null) {
            // Busca por estado y flag
            resultados = pedidoRepository.findByEstadoAndFlag(estado, 1); // Retorna la lista de pedidos por estado y flag
        }

        return resultados;
    }

    @Override
    public PedidosEntity actualizarPedido(Long id, Long idPersona, PedidosEntity pedido) {
        PedidosEntity pedidoExistente = pedidoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Error Pedido no existe"));
        pedidoExistente.setDescripcion(pedido.getDescripcion());
        pedidoExistente.setEstado(pedido.getEstado());
        return pedidoRepository.save(pedidoExistente);
    }

    @Override
    public void eliminarPedido(Long id) {
        PedidosEntity pedidosExistente = pedidoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Error Pedido no existe"));
        pedidosExistente.setFlag(0);
        pedidoRepository.save(pedidosExistente);
    }
}
