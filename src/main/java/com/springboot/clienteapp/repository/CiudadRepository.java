package com.springboot.clienteapp.repository;

import com.springboot.clienteapp.entity.Ciudad;
import org.springframework.data.repository.CrudRepository;

public interface CiudadRepository extends CrudRepository<Ciudad,Long> {

}
