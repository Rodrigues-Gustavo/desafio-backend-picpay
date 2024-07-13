package com.gustarodrigues.picpay.controllers.dtos;

import com.gustarodrigues.picpay.entities.Wallet;
import com.gustarodrigues.picpay.entities.WalletType;

public record CreateWalletDto(String fullName,
                              String document,
                              String email,
                              String password,
                              WalletType.Enum walletType) {

    public Wallet toWallet() {
        return new Wallet(
                fullName,
                document,
                email,
                password,
                walletType.get()
        );
    }
}
