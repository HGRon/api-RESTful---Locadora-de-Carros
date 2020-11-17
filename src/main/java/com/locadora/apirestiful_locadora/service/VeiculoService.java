package com.locadora.apirestiful_locadora.service;

import com.locadora.apirestiful_locadora.dto.VeiculoDTO;
import com.locadora.apirestiful_locadora.model.Veiculo;
import com.locadora.apirestiful_locadora.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> getAllVeiculos() {
        return veiculoRepository.getAllVeiculos();
    }

    public Veiculo getVeiculoById(int codigo){
        Optional<Veiculo> op = veiculoRepository.getVeiculoById(codigo);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo nao cadastrada: " + codigo));
    }

    public Veiculo fromDTO(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setValorDiaria(veiculoDTO.getValorDiaria());
        return veiculo;
    }

    public Veiculo salvar(Veiculo veiculo) {
        return veiculoRepository.salvar(veiculo);
    }

    public void removeById(int codigo) {
        veiculoRepository.remove(getVeiculoById(codigo));
    }

    public Veiculo atualizar(Veiculo veiculo) {
        getVeiculoById(veiculo.getCodigo());
        return veiculoRepository.atualizar(veiculo);
    }

}
