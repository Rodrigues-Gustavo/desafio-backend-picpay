package com.gustarodrigues.picpay.repositories;

import com.gustarodrigues.picpay.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
