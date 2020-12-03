package com.locadora.apirestiful_locadora.service;

import com.locadora.apirestiful_locadora.dto.ReservaDTO;
import com.locadora.apirestiful_locadora.dto.VeiculoDTO;
import com.locadora.apirestiful_locadora.model.Reserva;
import com.locadora.apirestiful_locadora.model.Veiculo;
import com.locadora.apirestiful_locadora.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public void isUsedDate(Reserva reserva) {
        if(reservaRepository.isUsedDate(reserva)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERRO, RESERVA JÁ REALIZADA PARA ESSE VEICULO, " +
                    "REFERENTE A DATA DEFINIDA ");
        }
    }

    public void isValidDate(ReservaDTO reservaDTO) {

        LocalDate dataAtual;

        dataAtual = LocalDate.now();

        if(reservaDTO.getDataFim() == null | reservaDTO.getDataFim() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERRO, DATA NÃO DEFINIDA ");
        } else if(reservaDTO.getDataInicio().isBefore(dataAtual)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERRO, DATA DE INICIO ANTERIOR A DATA ATUAL " + dataAtual);
        } else if(reservaDTO.getDataInicio().getDayOfWeek().getValue() == 7) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERRO, DATA INICIADA NO DOMINGO NÃO PERMITIDA");
        } else if(reservaDTO.getDataFim().getDayOfWeek().getValue() == 7) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERRO, DATA FINALIZADA NO DOMINGO NÃO PERMITIDA");
        } else if(reservaDTO.getDataInicio().isAfter(reservaDTO.getDataFim())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERRO, DATA DE FIM ANTERIO A DE INICIO!");
        }
    }

    public Reserva fromDTO(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        reserva.setDataInicio(reservaDTO.getDataInicio());
        reserva.setDataFim(reservaDTO.getDataFim());
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

    public List<Reserva> reservasCliente(int idCliente) {
        return reservaRepository.reservasCliente(idCliente);
    }

}
