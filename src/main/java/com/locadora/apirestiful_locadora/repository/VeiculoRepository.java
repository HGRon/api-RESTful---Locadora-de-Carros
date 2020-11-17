package com.locadora.apirestiful_locadora.repository;

import com.locadora.apirestiful_locadora.model.Veiculo;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class VeiculoRepository {

    private List<Veiculo> veiculos;
    private int nextId;

    @PostConstruct
    public void init() {

        Veiculo v1 = new Veiculo();
        v1.setCodigo(1);
        v1.setMarca("HONDA");
        v1.setModelo("CIVIC");
        v1.setValorDiaria(200.0);

        Veiculo v2 = new Veiculo();
        v2.setCodigo(2);
        v2.setMarca("HONDA");
        v2.setModelo("FIT");
        v2.setValorDiaria(100.0);

        veiculos = new ArrayList<Veiculo>();
        veiculos.add(v1);
        veiculos.add(v2);
        nextId = 3;

    }

    public List<Veiculo> getAllVeiculos() {
        return veiculos;
    }

    public Optional<Veiculo> getVeiculoById(int codigo){
        for (Veiculo veiculo : veiculos) {
            if(veiculo.getCodigo() == codigo){
                return Optional.of(veiculo);
            }
        }

        return Optional.empty();
    }

    public Veiculo salvar(Veiculo veiculo) {
        veiculo.setCodigo(nextId++);
        veiculos.add(veiculo);
        return veiculo;
    }

    public void remove(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }

    public Veiculo atualizar(Veiculo veiculo) {

        Veiculo veiculoAux = getVeiculoById(veiculo.getCodigo()).get();

        if(veiculoAux != null) {
            veiculoAux.setModelo(veiculo.getModelo());
            veiculoAux.setMarca(veiculo.getMarca());
            veiculoAux.setValorDiaria(veiculo.getValorDiaria());
        }

        return veiculoAux;
    }

}
