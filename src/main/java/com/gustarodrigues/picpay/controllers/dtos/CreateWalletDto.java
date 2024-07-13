package com.gustarodrigues.picpay.controllers.dtos;

import com.gustarodrigues.picpay.entities.Wallet;
import com.gustarodrigues.picpay.entities.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWalletDto(@NotBlank String fullName,
                              @NotBlank String document,
                              @NotBlank String email,
                              @NotBlank String password,
                              @NotNull WalletType.Enum walletType) {

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
