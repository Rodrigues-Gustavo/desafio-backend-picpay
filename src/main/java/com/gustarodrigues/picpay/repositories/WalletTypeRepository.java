package com.gustarodrigues.picpay.repositories;

import com.gustarodrigues.picpay.entities.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {
}
