package com.juian21oarte.thymeleafexample.repositories;

import com.juian21oarte.thymeleafexample.models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
