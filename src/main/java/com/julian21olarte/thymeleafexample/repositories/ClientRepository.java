package com.julian21olarte.thymeleafexample.repositories;

import com.julian21olarte.thymeleafexample.models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Optional<Client> findById(Long id);

    void deleteById(Long id);
}
