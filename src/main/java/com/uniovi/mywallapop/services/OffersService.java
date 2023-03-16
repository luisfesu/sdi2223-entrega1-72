package com.uniovi.mywallapop.services;

import com.uniovi.mywallapop.entities.Offer;
import com.uniovi.mywallapop.entities.User;
import com.uniovi.mywallapop.repositories.OffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


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
    public List<Offer> getOffersByBuyer(User user){
        return offersRepository.findAllByBuyer(user);
    }

    public Page<Offer> getAllOffers(Pageable pageable){
        Page<Offer> offers = offersRepository.findAll(pageable);
        return offers;
    }

    public Page<Offer> searchOfferByTitle(Pageable pageabe, String searchtext) {
        Page<Offer> offers;

        searchtext = "%" + searchtext + "%";

        offers = offersRepository.searchByTitle(pageabe, searchtext);
        return  offers;
    }
}
