package com.locadora.apirestiful_locadora.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class ClienteDTO {

    @NotBlank(message = "Nome eh obrigatorio!")
    @Length(min = 4, max = 200, message = "Nome minimo de 4 e o maximo de 200 caracteres!")
    private String nome;

    @NotBlank(message = "CPF eh obrigatorio!")
    @Length(min = 11, max = 11, message = "CPF minimo de 11 caracteres!")
    private String cpf;

    @NotBlank(message = "Contato eh obrigatorio!")
    @Length(min = 11, max = 20, message = "Contato minimo de 11 e o maximo de 20 caracteres!")
    private String contato;

    @NotBlank(message = "endereco eh obrigatorio!")
    @Length(min = 10, max = 200, message = "Endereco minimo de 11 e o maximo de 200 caracteres!")
    private String endereco;

    public ClienteDTO() {

    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
