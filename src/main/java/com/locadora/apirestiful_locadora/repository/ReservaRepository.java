package com.locadora.apirestiful_locadora.repository;

import com.locadora.apirestiful_locadora.model.Cliente;
import com.locadora.apirestiful_locadora.model.Reserva;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ReservaRepository {

    private List<Reserva> reservas;
    private int nextId;

    @PostConstruct
    public void init() {

    }

    public List<Reserva> getAllReservas() {
        return reservas;
    }

    public Optional<Reserva> getReservaById(int codigo){
        for (Reserva reserva : reservas) {
            if(reserva.getCodigo() == codigo){
                return Optional.of(reserva);
            }
        }

        return Optional.empty();
    }

    public Reserva salvar(Reserva reserva) {
        reserva.setCodigo(nextId++);
        reservas.add(reserva);
        return reserva;
    }

    public void remove(Reserva reserva) {
        reservas.remove(reserva);
    }

    public Reserva atualizar(Reserva reserva) {

        Reserva reservaAux = getReservaById(reserva.getCodigo()).get();

        if(reservaAux != null) {
            reservaAux.setDataFim(reserva.getDataFim());
            reservaAux.setDataInicio(reserva.getDataInicio());
            reservaAux.setValorTotal(reserva.getValorTotal());
        }

        return reservaAux;
    }
}
