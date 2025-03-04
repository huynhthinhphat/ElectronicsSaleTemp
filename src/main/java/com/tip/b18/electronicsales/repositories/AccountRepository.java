package com.tip.b18.electronicsales.repositories;

import com.tip.b18.electronicsales.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>{
    Optional<Account> findByUserName(String userName);
}
