package com.julian21olarte.thymeleafexample.services;

import com.julian21olarte.thymeleafexample.models.Client;
import com.julian21olarte.thymeleafexample.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Iterable<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return this.clientRepository.findById(id);
    }

    @Override
    public Client save(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    public void deleteById(Long id) {
        this.clientRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return this.clientRepository.existsById(id);
    }
}
