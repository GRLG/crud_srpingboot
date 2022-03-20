package com.springboot.clienteapp.service;

import com.springboot.clienteapp.entity.Ciudad;
import com.springboot.clienteapp.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CiudadServiceImpl implements ICiudadService{

    @Autowired
    private CiudadRepository ciudadRepository;


    @Override
    public List<Ciudad> listaCiudades() {
        return (List<Ciudad>) ciudadRepository.findAll();
    }
}
