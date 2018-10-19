package com.julian21olarte.thymeleafexample.repositories;

import com.julian21olarte.thymeleafexample.models.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Long> {
    Iterable<Product> findByNameContainingIgnoreCase(String term);
}
