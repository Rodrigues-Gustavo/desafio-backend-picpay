package com.gustarodrigues.picpay.services;

import com.gustarodrigues.picpay.controllers.dtos.TransferDto;
import com.gustarodrigues.picpay.entities.Transfer;
import com.gustarodrigues.picpay.entities.Wallet;
import com.gustarodrigues.picpay.exceptions.InsufficientBalanceException;
import com.gustarodrigues.picpay.exceptions.TransferNotAllowedForWalletTypeException;
import com.gustarodrigues.picpay.exceptions.TransferNotAuthorizedException;
import com.gustarodrigues.picpay.exceptions.WalletNotFoundException;
import com.gustarodrigues.picpay.repositories.TransferRepository;
import com.gustarodrigues.picpay.repositories.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AuthorizeService authorizeService;

    private final NotifyService notifyService;

    private final WalletRepository walletRepository;

    public TransferService(TransferRepository transferRepository, AuthorizeService authorizeService, NotifyService notifyService, WalletRepository walletRepository) {
        this.transferRepository = transferRepository;
        this.authorizeService = authorizeService;
        this.notifyService = notifyService;
        this.walletRepository = walletRepository;
    }


    @Transactional
    public Transfer transfer(TransferDto transferDto) {

        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender, receiver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notifyService.sendNotify(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {

        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalancerEqualOrGreatherThan(transferDto.value())) {
            throw new InsufficientBalanceException();
        }

        if (!authorizeService.isAuthorized(transferDto)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
