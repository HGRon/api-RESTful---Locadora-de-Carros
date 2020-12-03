package com.locadora.apirestiful_locadora.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class VeiculoDTO {

    @NotBlank(message = "Marca é obrigatorio!")
    @Length(min = 4, max = 200, message = "Marca mínimo de 4 e o máximo de 200 caracteres!")
    private String marca;

    @NotBlank(message = "Modelo é obrigatorio!")
    @Length(min = 4, max = 200, message = "Marca mínimo de 4 e o máximo de 200 caracteres!")
    private String modelo;

    @NotBlank(message = "Valor é obrigatorio!")
    @Min(value = 25, message = "Valor minimo de R$25")
    private Double valorDiaria;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }
}
