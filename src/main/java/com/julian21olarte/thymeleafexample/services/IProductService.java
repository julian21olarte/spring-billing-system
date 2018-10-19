package com.julian21olarte.thymeleafexample.services;

import com.julian21olarte.thymeleafexample.models.Product;

public interface IProductService {

    Iterable<Product> findProductsByName(String term);
}
