package com.julian21olarte.thymeleafexample.services;

import com.julian21olarte.thymeleafexample.models.Invite;
import com.julian21olarte.thymeleafexample.repositories.InviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InviteServiceImpl implements IInviteService {

    @Autowired
    private InviteRepository inviteRepository;

    @Override
    public Iterable<Invite> findAll() {
        return this.inviteRepository.findAll();
    }

    @Override
    public Invite save(Invite invite) {
        return this.inviteRepository.save(invite);
    }

    @Override
    public void deleteById(Long id) {
        this.inviteRepository.deleteById(id);
    }
}
