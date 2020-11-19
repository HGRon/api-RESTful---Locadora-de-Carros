package com.locadora.apirestiful_locadora.controllers;

import com.locadora.apirestiful_locadora.dto.ClienteDTO;
import com.locadora.apirestiful_locadora.dto.ReservaDTO;
import com.locadora.apirestiful_locadora.model.Cliente;
import com.locadora.apirestiful_locadora.model.Reserva;
import com.locadora.apirestiful_locadora.model.Veiculo;
import com.locadora.apirestiful_locadora.service.ClienteService;
import com.locadora.apirestiful_locadora.service.ReservaService;
import com.locadora.apirestiful_locadora.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<Reserva> getReservas() {
        return reservaService.getAllReservas();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable int codigo) {
        Reserva reserva = reservaService.getReservaById(codigo);
        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/clientes/{idCliente}")
    public List<Reserva> reservaCliente(@PathVariable int idCliente) {
        return clienteService.reservasCliente(idCliente);
    }

    @GetMapping("/veiculos/{idVeiculo}")
    public List<Reserva> reservaVeiculo(@PathVariable int idVeiculo) {
        return reservaService.reservasVeiculo(idVeiculo);
    }

    @PostMapping("clientes/{idCliente}/veiculos/{idVeiculo}")
    public ResponseEntity<Void> salvar(@RequestBody Reserva reserva,
                                       @PathVariable int idCliente,
                                       @PathVariable int idVeiculo,
                                       HttpServletRequest request,
                                       UriComponentsBuilder builder
    ) {

        clienteService.getClienteById(idCliente);
        veiculoService.getVeiculoById(idVeiculo);

        if(!reservaService.isValidDate(reserva)) {
            return new ResponseEntity("DATA INVALIDA", HttpStatus.NOT_ACCEPTABLE);
        } else if(reservaService.isUsedDate(reserva)){
            return new ResponseEntity("DATA JÁ USADA", HttpStatus.IM_USED);
        } else {
            Reserva reservaAux = reservaService.salvar(reserva);


            UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + reservaAux.getCodigo()).build();
            return ResponseEntity.created(uriComponents.toUri()).build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo){

        Reserva reserva = reservaService.getReservaById(codigo);

        if(reserva != null) {
            reservaService.removeById(codigo);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Reserva> atualizar(@PathVariable int codigo, @RequestBody ReservaDTO reservaDTO){

        Reserva reserva = reservaService.fromDTO(reservaDTO);
        reserva.setCodigo(codigo);
        reserva = reservaService.atualizar(reserva);
        return ResponseEntity.ok(reserva);
    }

}
