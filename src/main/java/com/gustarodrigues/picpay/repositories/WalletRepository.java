package com.gustarodrigues.picpay.repositories;

import com.gustarodrigues.picpay.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
