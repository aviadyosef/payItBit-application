package com.ay.payitbit.service;

import com.ay.payitbit.domain.InvoiceInfo;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link InvoiceInfo}.
 */
public interface InvoiceInfoService {
    /**
     * Save a invoiceInfo.
     *
     * @param invoiceInfo the entity to save.
     * @return the persisted entity.
     */
    InvoiceInfo save(InvoiceInfo invoiceInfo);

    /**
     * Partially updates a invoiceInfo.
     *
     * @param invoiceInfo the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InvoiceInfo> partialUpdate(InvoiceInfo invoiceInfo);

    /**
     * Get all the invoiceInfos.
     *
     * @return the list of entities.
     */
    List<InvoiceInfo> findAll();

    /**
     * Get the "id" invoiceInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvoiceInfo> findOne(Long id);

    /**
     * Delete the "id" invoiceInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
