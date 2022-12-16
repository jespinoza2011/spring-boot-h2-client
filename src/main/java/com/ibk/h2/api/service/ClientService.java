package com.ibk.h2.api.service;

import java.util.List;

import com.ibk.h2.api.model.Client;

public interface ClientService {

	List<Client> getClient();

	Client getClientById(Long id);
	
	Client getClientByCodigo(String codigo);
	
	String getCodigo(String codigo);

	Client insert(Client rate);

	void update(Long id, Client rate);

	void delete(Long clientId);
}
