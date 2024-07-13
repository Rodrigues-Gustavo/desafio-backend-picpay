package com.gustarodrigues.picpay.repositories;

import com.gustarodrigues.picpay.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByDocumentOrEmail(String document, String email);
}
