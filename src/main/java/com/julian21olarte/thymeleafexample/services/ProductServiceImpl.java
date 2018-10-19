package com.julian21olarte.thymeleafexample.services;

import com.julian21olarte.thymeleafexample.models.Product;
import com.julian21olarte.thymeleafexample.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductServiceImpl
 */
@Service
public class ProductServiceImpl implements IProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public Iterable<Product> findProductsByName(String term) {
		return this.productRepository.findByNameContainingIgnoreCase(term);
	}

  
}