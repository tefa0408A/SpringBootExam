package com.example.SpringBootExam.controller;

import com.example.SpringBootExam.constants.EstadosPedido;
import com.example.SpringBootExam.entity.PedidosEntity;
import com.example.SpringBootExam.entity.PersonaEntity;
import com.example.SpringBootExam.repository.PersonaRepository;
import com.example.SpringBootExam.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/v1/persona/{personaId}")
    public ResponseEntity<PedidosEntity> crearPedido(@PathVariable Long personaId, @RequestBody PedidosEntity pedido){
        /*PersonaEntity persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new NoSuchElementException("Error: Persona no existe"));

        pedido.setPersona(persona);*/
        PedidosEntity nuevoPedido = pedidoService.crearPedido(personaId,pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    @GetMapping("/v1/estado/{estado}")
    public ResponseEntity<List<PedidosEntity>> buscarPorEstado(@PathVariable String estado) {
        if (!estado.equals(EstadosPedido.PENDIENTE) &&
                !estado.equals(EstadosPedido.EN_PROCESO) &&
                !estado.equals(EstadosPedido.CANCELADO) &&
                !estado.equals(EstadosPedido.ENTREGADO)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<PedidosEntity> pedidos = pedidoService.obtenerTodos(estado);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/v1/buscar")
    public ResponseEntity<?> buscarPedidoPorParametro(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String estado) {
        List<PedidosEntity> pedidos = pedidoService.buscarPedidoPorParametro(id, estado);

        if (pedidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 si no se encuentra nada
        }

        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PutMapping("/v1/{idPedido}/persona/{idPersona}")
    public ResponseEntity<PedidosEntity> actualizarPedido(@PathVariable Long idPedido,
                                                         @PathVariable Long idPersona,
                                                         @RequestBody PedidosEntity pedido) {
        PedidosEntity pedidoActualizado = pedidoService.actualizarPedido(idPedido,idPersona ,pedido);
        return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/v1/eliminar/{id}")
    public  ResponseEntity<Void> eliminarPedido(@PathVariable Long id){
        pedidoService.eliminarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
