package com.springboot.clienteapp.service;

import com.springboot.clienteapp.entity.Cliente;

import java.util.List;

public interface IClienteService {

    List<Cliente> listartodos();
    void guardar(Cliente cliente);
    Cliente buscarporId(Long id);
    void eliminar(Long id);



}
