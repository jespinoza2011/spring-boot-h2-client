package com.ibk.h2.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ibk.h2.api.model.Client;
import com.ibk.h2.api.repository.ClientRepository;
import com.ibk.h2.api.utilitario.Utilitario;

@Service
public class ClientServiceImpl implements ClientService {

	ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<Client> getClient() {
		List<Client> clients = new ArrayList<>();
		clientRepository.findAll().forEach(clients::add);
		return clients;
	}

	@Override
	public Client getClientById(Long id) {
		return clientRepository.findById(id).get();
	}

	@Override
	public Client getClientByCodigo(String codigo) {
		List<Client> clients = getClient();
		Client client = null;
		try {
			client = clients.stream().filter(t -> codigo.equals(t.getCodigo())).findFirst().orElse(null);
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return client;
	}
	
	@Override
	public String getCodigo(String codigo) {
		String decrypted = null;
		try {
			decrypted = Utilitario.decrypt(codigo);
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return decrypted;
	}

	@Override
	public Client insert(Client client) {
		boolean Isvalid = true;
		Client result = null;
		try {
			String pass = client.getCodigo();
			String passEncrip = Utilitario.encrypt(pass);
			System.out.println("encrypt:" + passEncrip);
			if (Isvalid) {
				client.setCodigo(passEncrip);
				result = clientRepository.save(client);
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return result;
	}

	@Override
	public void update(Long id, Client client) {
		Client clientFromDb = clientRepository.findById(id).get();
		System.out.println(clientFromDb.toString());
		clientFromDb.setCodigo(client.getCodigo());
		clientFromDb.setNombre(client.getNombre());
		clientFromDb.setApeMaterno(client.getApeMaterno());
		clientFromDb.setApePaterno(client.getApePaterno());
		clientFromDb.setTipoDocumento(client.getTipoDocumento());
		clientFromDb.setNumeroDocumento(client.getNumeroDocumento());
		clientRepository.save(clientFromDb);
	}

	@Override
	public void delete(Long clientId) {
		clientRepository.deleteById(clientId);
	}
}