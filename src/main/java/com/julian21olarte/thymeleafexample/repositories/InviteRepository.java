package com.julian21olarte.thymeleafexample.repositories;

import com.julian21olarte.thymeleafexample.models.Invite;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InviteRepository extends CrudRepository<Invite, Long> {
    Optional<Invite> findById(Long id);
    void deleteById(Long id);
}
