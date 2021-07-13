package com.ay.payitbit.service.impl;

import com.ay.payitbit.domain.Wallet;
import com.ay.payitbit.repository.WalletRepository;
import com.ay.payitbit.service.WalletService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Wallet}.
 */
@Service
@Transactional
public class WalletServiceImpl implements WalletService {

    private final Logger log = LoggerFactory.getLogger(WalletServiceImpl.class);

    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet save(Wallet wallet) {
        log.debug("Request to save Wallet : {}", wallet);
        return walletRepository.save(wallet);
    }

    @Override
    public Optional<Wallet> partialUpdate(Wallet wallet) {
        log.debug("Request to partially update Wallet : {}", wallet);

        return walletRepository
            .findById(wallet.getId())
            .map(
                existingWallet -> {
                    if (wallet.getReadableName() != null) {
                        existingWallet.setReadableName(wallet.getReadableName());
                    }
                    if (wallet.getWalletType() != null) {
                        existingWallet.setWalletType(wallet.getWalletType());
                    }

                    return existingWallet;
                }
            )
            .map(walletRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wallet> findAll() {
        log.debug("Request to get all Wallets");
        return walletRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Wallet> findOne(Long id) {
        log.debug("Request to get Wallet : {}", id);
        return walletRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Wallet : {}", id);
        walletRepository.deleteById(id);
    }
}
