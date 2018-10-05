package com.julian21olarte.thymeleafexample.repositories;

import com.julian21olarte.thymeleafexample.models.Bill;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BillRepository extends CrudRepository<Bill, Long> {
    Optional<Bill> findById(Long id);
    void deleteById(Long id);
}
