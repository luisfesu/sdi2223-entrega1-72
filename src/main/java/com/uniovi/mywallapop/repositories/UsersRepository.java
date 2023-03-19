package com.uniovi.mywallapop.repositories;

import com.uniovi.mywallapop.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UsersRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User SET money = money - ?2 WHERE id = ?1")
    void updateMoney(Long id, Double price);
}
