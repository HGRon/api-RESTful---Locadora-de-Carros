package com.locadora.apirestiful_locadora.controllers;

import com.locadora.apirestiful_locadora.dto.ClienteDTO;
import com.locadora.apirestiful_locadora.model.Cliente;
import com.locadora.apirestiful_locadora.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable int codigo) {
        Cliente cliente = clienteService.getClienteById(codigo);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody ClienteDTO clienteDTO,
                                       HttpServletRequest request,
                                       UriComponentsBuilder builder
    ) {

        Cliente clienteAux = clienteService.salvar(clienteService.fromDTO(clienteDTO));
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + clienteAux.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo){

        Cliente cliente = clienteService.getClienteById(codigo);

        if(cliente != null) {
            clienteService.removeById(codigo);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Cliente> atualizar(@PathVariable int codigo,@Valid @RequestBody ClienteDTO clienteDTO){

        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente.setCodigo(codigo);
        cliente = clienteService.atualizar(cliente);
        return ResponseEntity.ok(cliente);
    }

}
