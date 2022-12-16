package com.ibk.h2.api.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibk.h2.api.model.Client;
import com.ibk.h2.api.service.ClientService;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

	private ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping
	public ResponseEntity<List<Client>> getAll() {
		List<Client> client = clientService.getClient();
		return new ResponseEntity<>(client, HttpStatus.OK);
	}

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<Client> getClient(@PathVariable String codigo) {
		return new ResponseEntity<>(clientService.getClientByCodigo(codigo), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/decrypt/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<String> getCodigoUnico(@PathVariable String codigo) {
		return new ResponseEntity<>(clientService.getCodigo(codigo), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Client> save(@RequestBody Client client) {
		Client client1 = clientService.insert(client);
		HttpHeaders httpHeaders = new HttpHeaders();
		if (client1 == null) {
			return new ResponseEntity<>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			httpHeaders.add("client", "/api/v1/client/" + client1.getId().toString());
			return new ResponseEntity<>(client1, httpHeaders, HttpStatus.CREATED);
		}
	}

	@PutMapping({ "/{id}" })
	public ResponseEntity<Client> update(@PathVariable("id") Long id, @RequestBody Client client) {
		clientService.update(id, client);
		return new ResponseEntity<>(clientService.getClientById(id), HttpStatus.OK);
	}

	@DeleteMapping({ "/{id}" })
	public ResponseEntity<Client> delete(@PathVariable("id") Long id) {
		clientService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}