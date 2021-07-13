package com.ay.payitbit.service.impl;

import com.ay.payitbit.domain.InvoiceInfo;
import com.ay.payitbit.repository.InvoiceInfoRepository;
import com.ay.payitbit.service.InvoiceInfoService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InvoiceInfo}.
 */
@Service
@Transactional
public class InvoiceInfoServiceImpl implements InvoiceInfoService {

    private final Logger log = LoggerFactory.getLogger(InvoiceInfoServiceImpl.class);

    private final InvoiceInfoRepository invoiceInfoRepository;

    public InvoiceInfoServiceImpl(InvoiceInfoRepository invoiceInfoRepository) {
        this.invoiceInfoRepository = invoiceInfoRepository;
    }

    @Override
    public InvoiceInfo save(InvoiceInfo invoiceInfo) {
        log.debug("Request to save InvoiceInfo : {}", invoiceInfo);
        return invoiceInfoRepository.save(invoiceInfo);
    }

    @Override
    public Optional<InvoiceInfo> partialUpdate(InvoiceInfo invoiceInfo) {
        log.debug("Request to partially update InvoiceInfo : {}", invoiceInfo);

        return invoiceInfoRepository
            .findById(invoiceInfo.getId())
            .map(
                existingInvoiceInfo -> {
                    if (invoiceInfo.getAmount() != null) {
                        existingInvoiceInfo.setAmount(invoiceInfo.getAmount());
                    }
                    if (invoiceInfo.getInvoiceNumber() != null) {
                        existingInvoiceInfo.setInvoiceNumber(invoiceInfo.getInvoiceNumber());
                    }
                    if (invoiceInfo.getCurrency() != null) {
                        existingInvoiceInfo.setCurrency(invoiceInfo.getCurrency());
                    }
                    if (invoiceInfo.getInvoicePicture() != null) {
                        existingInvoiceInfo.setInvoicePicture(invoiceInfo.getInvoicePicture());
                    }
                    if (invoiceInfo.getInvoicePictureContentType() != null) {
                        existingInvoiceInfo.setInvoicePictureContentType(invoiceInfo.getInvoicePictureContentType());
                    }
                    if (invoiceInfo.getAgreement() != null) {
                        existingInvoiceInfo.setAgreement(invoiceInfo.getAgreement());
                    }
                    if (invoiceInfo.getAgreementContentType() != null) {
                        existingInvoiceInfo.setAgreementContentType(invoiceInfo.getAgreementContentType());
                    }
                    if (invoiceInfo.getServiceDescription() != null) {
                        existingInvoiceInfo.setServiceDescription(invoiceInfo.getServiceDescription());
                    }

                    return existingInvoiceInfo;
                }
            )
            .map(invoiceInfoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceInfo> findAll() {
        log.debug("Request to get all InvoiceInfos");
        return invoiceInfoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceInfo> findOne(Long id) {
        log.debug("Request to get InvoiceInfo : {}", id);
        return invoiceInfoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvoiceInfo : {}", id);
        invoiceInfoRepository.deleteById(id);
    }
}
