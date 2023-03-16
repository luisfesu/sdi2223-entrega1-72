package com.uniovi.mywallapop.services;

import com.uniovi.mywallapop.entities.Offer;
import com.uniovi.mywallapop.repositories.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffersService {
    @Autowired
    private OffersRepository offersRepository;

    public Offer getOffer(Long id) {
        return offersRepository.findById(id).get();
    }

    public void addOffer(Offer offer) {
        offersRepository.save(offer);
    }
}
