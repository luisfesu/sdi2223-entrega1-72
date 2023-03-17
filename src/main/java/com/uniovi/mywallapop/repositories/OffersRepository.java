package com.uniovi.mywallapop.repositories;

import com.uniovi.mywallapop.entities.Offer;
import com.uniovi.mywallapop.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OffersRepository extends CrudRepository<Offer, Long> {
    List<Offer> findAllByBuyer(User user);

    Page<Offer> findAll(Pageable pageable);

    @Query("select o FROM Offer o WHERE (LOWER(o.title) LIKE LOWER(?1))")
    Page<Offer> searchByTitle(Pageable pageable, String searchtext);

    /***
     * Devuelve las ofertas pertenecientes al usuario registrado actual. La oferta pertenece a un usuario si ha sido
     * este el que la ha creado.
     * @param user Usuario actual registrado en la aplicación.
     * @return Lista de Ofertas para creadas por el usuario registrado.
     */
    List<Offer> findAllByUser(User user);
}
