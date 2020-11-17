package com.locadora.apirestiful_locadora.controllers;

import com.locadora.apirestiful_locadora.dto.VeiculoDTO;
import com.locadora.apirestiful_locadora.model.Veiculo;
import com.locadora.apirestiful_locadora.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> getVeiculos() {
        return veiculoService.getAllVeiculos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Veiculo> getVeiculoById(@PathVariable int codigo) {
        Veiculo veiculo = veiculoService.getVeiculoById(codigo);
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Veiculo veiculo,
                                       HttpServletRequest request,
                                       UriComponentsBuilder builder
    ) {
        Veiculo veiculoAux = veiculoService.salvar(veiculo);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + veiculoAux.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo){

        Veiculo veiculo = veiculoService.getVeiculoById(codigo);

        if(veiculo != null) {
            veiculoService.removeById(codigo);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable int codigo, @RequestBody VeiculoDTO veiculoDTO){

        Veiculo veiculo = veiculoService.fromDTO(veiculoDTO);
        veiculo.setCodigo(codigo);
        veiculo = veiculoService.atualizar(veiculo);
        return ResponseEntity.ok(veiculo);
    }

}
