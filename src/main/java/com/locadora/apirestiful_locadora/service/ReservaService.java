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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public boolean isUsedDate(Reserva reserva) {
        return reservaRepository.isUsedDate(reserva);
    }

    public boolean isValidDate(Reserva reserva) {

        LocalDate dataAtual;

        dataAtual = LocalDate.now();

        if (
                reserva.getDataInicio().isBefore(dataAtual) |
                reserva.getDataInicio().getDayOfWeek().getValue() == 7 |
                reserva.getDataFim().getDayOfWeek().getValue() == 7 |
                reserva.getDataInicio().isAfter(reserva.getDataFim())
        ) {
            return false;
        } else {
            return true;
        }

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

    public List<Reserva> reservasVeiculo(int idVeiculo) {
        return reservaRepository.reservasVeiculo(idVeiculo);
    }
}
