package com.example.SpringBootExam.repository;

import com.example.SpringBootExam.entity.PedidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidosEntity,Long> {
    List<PedidosEntity> findByEstadoAndFlag(String estado, int flag);
}
