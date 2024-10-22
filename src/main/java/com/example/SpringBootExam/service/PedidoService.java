package com.example.SpringBootExam.service;

import com.example.SpringBootExam.entity.PedidosEntity;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    PedidosEntity crearPedido(Long personaId, PedidosEntity pedido);
    List<PedidosEntity> obtenerTodos(String estado);
    List<PedidosEntity> buscarPedidoPorParametro(Long id, String estado);
    PedidosEntity actualizarPedido(Long id, Long idPersona ,PedidosEntity pedido);
    void eliminarPedido(Long id);
}
