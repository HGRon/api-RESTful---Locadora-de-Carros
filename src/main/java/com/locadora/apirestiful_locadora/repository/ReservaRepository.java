package com.locadora.apirestiful_locadora.repository;

import com.locadora.apirestiful_locadora.model.Reserva;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ReservaRepository {

    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private int nextId;

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

    public List<Reserva> reservasVeiculo(int idVeiculo) {

        ArrayList<Reserva> reservasVeiculo = new ArrayList<Reserva>();

        for (Reserva reserva : reservas) {
            if (reserva.getCodigoVeiculo() == idVeiculo) {
                reservasVeiculo.add(reserva);
            }
        }

        return reservasVeiculo;
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

    public boolean isUsedDate(Reserva reserva) {

        for (Reserva reservaAux : reservas) {

            if (
                    (reserva.getCodigoVeiculo() == reservaAux.getCodigoVeiculo()) &
                            (
                                    (
                                            reserva.getDataInicio().isAfter(reservaAux.getDataInicio()) &
                                            reserva.getDataInicio().isBefore(reservaAux.getDataFim())
                                    ) | (
                                            reserva.getDataFim().isAfter(reservaAux.getDataInicio()) &
                                            reserva.getDataInicio().isBefore(reservaAux.getDataFim())
                                    )
                            )
            ){
                return true;
            }
        }

        return false;
    }


}
