package com.ay.payitbit.service;

import com.ay.payitbit.domain.SupplierInfo;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link SupplierInfo}.
 */
public interface SupplierInfoService {
    /**
     * Save a supplierInfo.
     *
     * @param supplierInfo the entity to save.
     * @return the persisted entity.
     */
    SupplierInfo save(SupplierInfo supplierInfo);

    /**
     * Partially updates a supplierInfo.
     *
     * @param supplierInfo the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SupplierInfo> partialUpdate(SupplierInfo supplierInfo);

    /**
     * Get all the supplierInfos.
     *
     * @return the list of entities.
     */
    List<SupplierInfo> findAll();

    /**
     * Get the "id" supplierInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SupplierInfo> findOne(Long id);

    /**
     * Delete the "id" supplierInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
