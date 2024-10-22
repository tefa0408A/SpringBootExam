package com.example.SpringBootExam.controller;

import com.example.SpringBootExam.entity.PedidosEntity;
import com.example.SpringBootExam.entity.PersonaEntity;
import com.example.SpringBootExam.service.PedidoService;
import com.example.SpringBootExam.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/v1/crear")
    public ResponseEntity<PersonaEntity> crearPersona(@RequestBody PersonaEntity persona){
        PersonaEntity nuevaPersona = personaService.crearPersona(persona);
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }

    @GetMapping("/v1/obtener")
    public List<PersonaEntity> buscarTodos(){
        return personaService.buscarTodos();
    }

    @GetMapping("v1/obtener/{numDocument}")
    public PersonaEntity buscarPersonaXnumDocumento(@PathVariable Integer numDocument) {
        return personaService.buscarPersonaXnumDocumento(numDocument);
    }

    @PutMapping("/v1/actualizar/{id}")
    public ResponseEntity<PersonaEntity> actualizarPersona(@PathVariable Long id, @RequestBody PersonaEntity persona){
        PersonaEntity personaActualizada = personaService.actualizarPersona(id, persona);
        return new ResponseEntity<>(personaActualizada,HttpStatus.OK);
    }

    @DeleteMapping("/v1/eliminar/{id}")
    public  ResponseEntity<Void> eliminarPersona(@PathVariable Long id){
        personaService.eliminarPersona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
