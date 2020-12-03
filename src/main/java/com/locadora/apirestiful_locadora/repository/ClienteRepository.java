package com.locadora.apirestiful_locadora.repository;

import com.locadora.apirestiful_locadora.model.Cliente;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ClienteRepository {

    private List<Cliente> clientes;
    private int nextId;

    @PostConstruct
    public void init() {

        Cliente c1 = new Cliente();
        c1.setCodigo(1);
        c1.setNome("JOÃO SILVA");
        c1.setCpf("123.123.123-45");
        c1.setContato("11 912341234");
        c1.setEndereco("Bairro x, rua y, numero 20");

        Cliente c2 = new Cliente();
        c2.setCodigo(2);
        c2.setNome("LILI SANTOS");
        c2.setCpf("123.123.123-45");
        c2.setContato("11 912341234");
        c2.setEndereco("Bairro x, rua y, numero 20");

        clientes = new ArrayList<>();
        clientes.add(c1);
        clientes.add(c2);
        nextId = 3;

    }

    public List<Cliente> getAllClientes() {
        return clientes;
    }

    public Optional<Cliente> getClienteById(int codigo){
        for (Cliente cliente : clientes) {
            if(cliente.getCodigo() == codigo){
                return Optional.of(cliente);
            }
        }

        return Optional.empty();
    }

    public Cliente salvar(Cliente cliente) {
        cliente.setCodigo(nextId++);
        clientes.add(cliente);
        return cliente;
    }

    public void remove(Cliente cliente) {
        clientes.remove(cliente);
    }

    public Cliente atualizar(Cliente cliente) {

        Cliente clienteAux = getClienteById(cliente.getCodigo()).get();

        if(clienteAux != null) {
            clienteAux.setNome(cliente.getNome());
            clienteAux.setCpf(cliente.getCpf());
            clienteAux.setContato(cliente.getContato());
        }

        return clienteAux;
    }

}
