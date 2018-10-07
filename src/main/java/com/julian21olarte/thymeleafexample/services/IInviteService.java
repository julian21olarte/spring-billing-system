package com.julian21olarte.thymeleafexample.services;

import com.julian21olarte.thymeleafexample.models.Bill;
import com.julian21olarte.thymeleafexample.models.Invite;

import java.util.Optional;

public interface IInviteService {
    Iterable<Invite> findAll();
    Invite save(Invite invite);
    void deleteById(Long id);
}
