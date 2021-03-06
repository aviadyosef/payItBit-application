package com.ay.payitbit.service.impl;

import com.ay.payitbit.domain.Invoice;
import com.ay.payitbit.repository.InvoiceRepository;
import com.ay.payitbit.service.InvoiceService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Invoice}.
 */
@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice save(Invoice invoice) {
        log.debug("Request to save Invoice : {}", invoice);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Optional<Invoice> partialUpdate(Invoice invoice) {
        log.debug("Request to partially update Invoice : {}", invoice);

        return invoiceRepository
            .findById(invoice.getId())
            .map(
                existingInvoice -> {
                    if (invoice.getSeqNo() != null) {
                        existingInvoice.setSeqNo(invoice.getSeqNo());
                    }
                    if (invoice.getCreatedOn() != null) {
                        existingInvoice.setCreatedOn(invoice.getCreatedOn());
                    }
                    if (invoice.getInvoiceNumber() != null) {
                        existingInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
                    }
                    if (invoice.getCompanyName() != null) {
                        existingInvoice.setCompanyName(invoice.getCompanyName());
                    }
                    if (invoice.getCreatedBy() != null) {
                        existingInvoice.setCreatedBy(invoice.getCreatedBy());
                    }
                    if (invoice.getAction() != null) {
                        existingInvoice.setAction(invoice.getAction());
                    }

                    return existingInvoice;
                }
            )
            .map(invoiceRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Invoice> findAll(Pageable pageable) {
        log.debug("Request to get all Invoices");
        return invoiceRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Invoice> findOne(Long id) {
        log.debug("Request to get Invoice : {}", id);
        return invoiceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Invoice : {}", id);
        invoiceRepository.deleteById(id);
    }
}
