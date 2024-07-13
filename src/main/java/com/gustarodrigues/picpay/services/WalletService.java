package com.gustarodrigues.picpay.services;

import com.gustarodrigues.picpay.controllers.dtos.CreateWalletDto;
import com.gustarodrigues.picpay.entities.Wallet;
import com.gustarodrigues.picpay.exceptions.WalletDataAlreadyExistsException;
import com.gustarodrigues.picpay.repositories.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {

        var walletDb = walletRepository.findByDocumentOrEmail(dto.document(), dto.email());
        if (walletDb.isPresent()) {
            throw new WalletDataAlreadyExistsException("Document or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }
}
