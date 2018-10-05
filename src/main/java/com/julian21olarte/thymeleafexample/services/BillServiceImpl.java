package com.julian21olarte.thymeleafexample.services;

import com.julian21olarte.thymeleafexample.models.Bill;
import com.julian21olarte.thymeleafexample.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillServiceImpl implements IBillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public Iterable<Bill> findAll() {
        return this.billRepository.findAll();
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return this.billRepository.findById(id);
    }

    @Override
    public Bill save(Bill bill) {
        return this.billRepository.save(bill);
    }

    @Override
    public void deleteById(Long id) {
        this.billRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return this.billRepository.existsById(id);
    }
}
