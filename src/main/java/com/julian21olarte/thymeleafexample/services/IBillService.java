package com.julian21olarte.thymeleafexample.services;

import com.julian21olarte.thymeleafexample.models.Bill;

import java.util.Optional;

public interface IBillService {

    Iterable<Bill> findAll();
    Optional<Bill> findById(Long id);
    Bill save(Bill bill);
    void deleteById(Long id);
    boolean existById(Long id);
}
