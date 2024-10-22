package com.example.SpringBootExam.repository;

import com.example.SpringBootExam.entity.PedidosEntity;
import com.example.SpringBootExam.entity.PersonaEntity;
import com.example.SpringBootExam.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity,Long> {

    PersonaEntity findByNumDocument(Integer numDocument);
    List<PersonaEntity> findByState(Integer state);
}
