package com.springboot.clienteapp.service;

import com.springboot.clienteapp.entity.Cliente;
import com.springboot.clienteapp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteSreviceImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listartodos() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public void guardar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarporId(Long id) {
        return clienteRepository.findById(id).orElse(null);

    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
