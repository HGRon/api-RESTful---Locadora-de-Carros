package com.locadora.apirestiful_locadora.service;

import com.locadora.apirestiful_locadora.dto.ReservaDTO;
import com.locadora.apirestiful_locadora.dto.VeiculoDTO;
import com.locadora.apirestiful_locadora.model.Reserva;
import com.locadora.apirestiful_locadora.model.Veiculo;
import com.locadora.apirestiful_locadora.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> getAllReservas() {
        return reservaRepository.getAllReservas();
    }

    public Reserva getReservaById(int codigo){
        Optional<Reserva> op = reservaRepository.getReservaById(codigo);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva nao cadastrada: " + codigo));
    }

    public Reserva fromDTO(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        reserva.setDataInicio(reservaDTO.getDataInicio());
        reserva.setDataFim(reservaDTO.getDataFim());
        reserva.setValorTotal(reservaDTO.getValorTotal());
        return reserva;
    }

    public Reserva salvar(Reserva reserva) {
        return reservaRepository.salvar(reserva);
    }

    public void removeById(int codigo) {
        reservaRepository.remove(getReservaById(codigo));
    }

    public Reserva atualizar(Reserva reserva) {
        getReservaById(reserva.getCodigo());
        return reservaRepository.atualizar(reserva);
    }

}
