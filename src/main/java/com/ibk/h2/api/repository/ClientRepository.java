package com.ibk.h2.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibk.h2.api.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

}