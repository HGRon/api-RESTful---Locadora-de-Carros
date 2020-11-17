package com.locadora.apirestiful_locadora.service;

import com.locadora.apirestiful_locadora.dto.ClienteDTO;
import com.locadora.apirestiful_locadora.model.Cliente;
import com.locadora.apirestiful_locadora.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.getAllClientes();
    }

    public Cliente getClienteById(int codigo){
        Optional<Cliente> op = clienteRepository.getClienteById(codigo);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao cadastrada: " + codigo));
    }

    public Cliente fromDTO(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setContato(clienteDTO.getContato());
        return cliente;
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.salvar(cliente);
    }

    public void removeById(int codigo) {
        clienteRepository.remove(getClienteById(codigo));
    }

    public Cliente atualizar(Cliente cliente) {
        getClienteById(cliente.getCodigo());
        return clienteRepository.atualizar(cliente);
    }

}
