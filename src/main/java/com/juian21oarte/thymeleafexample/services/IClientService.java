package com.juian21oarte.thymeleafexample.services;

import com.juian21oarte.thymeleafexample.models.Client;

import java.util.Optional;

public interface IClientService {

    Iterable<Client> findAll();
    Optional<Client> findById(Long id);
    Client save(Client client);
    void deleteById(Long id);
    boolean existById(Long id);
}
