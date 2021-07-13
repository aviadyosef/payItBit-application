package com.ay.payitbit.service.impl;

import com.ay.payitbit.domain.SupplierInfo;
import com.ay.payitbit.repository.SupplierInfoRepository;
import com.ay.payitbit.service.SupplierInfoService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SupplierInfo}.
 */
@Service
@Transactional
public class SupplierInfoServiceImpl implements SupplierInfoService {

    private final Logger log = LoggerFactory.getLogger(SupplierInfoServiceImpl.class);

    private final SupplierInfoRepository supplierInfoRepository;

    public SupplierInfoServiceImpl(SupplierInfoRepository supplierInfoRepository) {
        this.supplierInfoRepository = supplierInfoRepository;
    }

    @Override
    public SupplierInfo save(SupplierInfo supplierInfo) {
        log.debug("Request to save SupplierInfo : {}", supplierInfo);
        return supplierInfoRepository.save(supplierInfo);
    }

    @Override
    public Optional<SupplierInfo> partialUpdate(SupplierInfo supplierInfo) {
        log.debug("Request to partially update SupplierInfo : {}", supplierInfo);

        return supplierInfoRepository
            .findById(supplierInfo.getId())
            .map(
                existingSupplierInfo -> {
                    if (supplierInfo.getCompanyName() != null) {
                        existingSupplierInfo.setCompanyName(supplierInfo.getCompanyName());
                    }
                    if (supplierInfo.getNumber() != null) {
                        existingSupplierInfo.setNumber(supplierInfo.getNumber());
                    }
                    if (supplierInfo.getAddress() != null) {
                        existingSupplierInfo.setAddress(supplierInfo.getAddress());
                    }
                    if (supplierInfo.getWebsite() != null) {
                        existingSupplierInfo.setWebsite(supplierInfo.getWebsite());
                    }

                    return existingSupplierInfo;
                }
            )
            .map(supplierInfoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplierInfo> findAll() {
        log.debug("Request to get all SupplierInfos");
        return supplierInfoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SupplierInfo> findOne(Long id) {
        log.debug("Request to get SupplierInfo : {}", id);
        return supplierInfoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SupplierInfo : {}", id);
        supplierInfoRepository.deleteById(id);
    }
}
