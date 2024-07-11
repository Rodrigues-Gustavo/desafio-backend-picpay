package com.gustarodrigues.picpay.config;

import com.gustarodrigues.picpay.entities.WalletType;
import com.gustarodrigues.picpay.repositories.WalletTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    @Autowired
    private final WalletTypeRepository walletTypeRepository;

    public DataLoader(WalletTypeRepository walletTypeRepository) {
        this.walletTypeRepository = walletTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream((WalletType.Enum.values())).forEach(walletType -> walletTypeRepository.save(walletType.get()));
    }
}
