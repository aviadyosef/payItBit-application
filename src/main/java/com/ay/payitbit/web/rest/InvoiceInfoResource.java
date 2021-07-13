package com.ay.payitbit.web.rest;

import com.ay.payitbit.domain.InvoiceInfo;
import com.ay.payitbit.repository.InvoiceInfoRepository;
import com.ay.payitbit.service.InvoiceInfoService;
import com.ay.payitbit.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ay.payitbit.domain.InvoiceInfo}.
 */
@RestController
@RequestMapping("/api")
public class InvoiceInfoResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceInfoResource.class);

    private static final String ENTITY_NAME = "invoiceInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceInfoService invoiceInfoService;

    private final InvoiceInfoRepository invoiceInfoRepository;

    public InvoiceInfoResource(InvoiceInfoService invoiceInfoService, InvoiceInfoRepository invoiceInfoRepository) {
        this.invoiceInfoService = invoiceInfoService;
        this.invoiceInfoRepository = invoiceInfoRepository;
    }

    /**
     * {@code POST  /invoice-infos} : Create a new invoiceInfo.
     *
     * @param invoiceInfo the invoiceInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoiceInfo, or with status {@code 400 (Bad Request)} if the invoiceInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-infos")
    public ResponseEntity<InvoiceInfo> createInvoiceInfo(@RequestBody InvoiceInfo invoiceInfo) throws URISyntaxException {
        log.debug("REST request to save InvoiceInfo : {}", invoiceInfo);
        if (invoiceInfo.getId() != null) {
            throw new BadRequestAlertException("A new invoiceInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvoiceInfo result = invoiceInfoService.save(invoiceInfo);
        return ResponseEntity
            .created(new URI("/api/invoice-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invoice-infos/:id} : Updates an existing invoiceInfo.
     *
     * @param id the id of the invoiceInfo to save.
     * @param invoiceInfo the invoiceInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceInfo,
     * or with status {@code 400 (Bad Request)} if the invoiceInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoiceInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-infos/{id}")
    public ResponseEntity<InvoiceInfo> updateInvoiceInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InvoiceInfo invoiceInfo
    ) throws URISyntaxException {
        log.debug("REST request to update InvoiceInfo : {}, {}", id, invoiceInfo);
        if (invoiceInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, invoiceInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!invoiceInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InvoiceInfo result = invoiceInfoService.save(invoiceInfo);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, invoiceInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /invoice-infos/:id} : Partial updates given fields of an existing invoiceInfo, field will ignore if it is null
     *
     * @param id the id of the invoiceInfo to save.
     * @param invoiceInfo the invoiceInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceInfo,
     * or with status {@code 400 (Bad Request)} if the invoiceInfo is not valid,
     * or with status {@code 404 (Not Found)} if the invoiceInfo is not found,
     * or with status {@code 500 (Internal Server Error)} if the invoiceInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/invoice-infos/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<InvoiceInfo> partialUpdateInvoiceInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InvoiceInfo invoiceInfo
    ) throws URISyntaxException {
        log.debug("REST request to partial update InvoiceInfo partially : {}, {}", id, invoiceInfo);
        if (invoiceInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, invoiceInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!invoiceInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InvoiceInfo> result = invoiceInfoService.partialUpdate(invoiceInfo);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, invoiceInfo.getId().toString())
        );
    }

    /**
     * {@code GET  /invoice-infos} : get all the invoiceInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceInfos in body.
     */
    @GetMapping("/invoice-infos")
    public List<InvoiceInfo> getAllInvoiceInfos() {
        log.debug("REST request to get all InvoiceInfos");
        return invoiceInfoService.findAll();
    }

    /**
     * {@code GET  /invoice-infos/:id} : get the "id" invoiceInfo.
     *
     * @param id the id of the invoiceInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoiceInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-infos/{id}")
    public ResponseEntity<InvoiceInfo> getInvoiceInfo(@PathVariable Long id) {
        log.debug("REST request to get InvoiceInfo : {}", id);
        Optional<InvoiceInfo> invoiceInfo = invoiceInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoiceInfo);
    }

    /**
     * {@code DELETE  /invoice-infos/:id} : delete the "id" invoiceInfo.
     *
     * @param id the id of the invoiceInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-infos/{id}")
    public ResponseEntity<Void> deleteInvoiceInfo(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceInfo : {}", id);
        invoiceInfoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
