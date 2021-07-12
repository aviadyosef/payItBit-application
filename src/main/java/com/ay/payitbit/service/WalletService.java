package com.ay.payitbit.service;

import com.ay.payitbit.domain.Wallet;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Wallet}.
 */
public interface WalletService {
    /**
     * Save a wallet.
     *
     * @param wallet the entity to save.
     * @return the persisted entity.
     */
    Wallet save(Wallet wallet);

    /**
     * Partially updates a wallet.
     *
     * @param wallet the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Wallet> partialUpdate(Wallet wallet);

    /**
     * Get all the wallets.
     *
     * @return the list of entities.
     */
    List<Wallet> findAll();

    /**
     * Get the "id" wallet.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Wallet> findOne(Long id);

    /**
     * Delete the "id" wallet.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
