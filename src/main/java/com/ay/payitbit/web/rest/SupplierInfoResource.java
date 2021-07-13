package com.ay.payitbit.web.rest;

import com.ay.payitbit.domain.SupplierInfo;
import com.ay.payitbit.repository.SupplierInfoRepository;
import com.ay.payitbit.service.SupplierInfoService;
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
 * REST controller for managing {@link com.ay.payitbit.domain.SupplierInfo}.
 */
@RestController
@RequestMapping("/api")
public class SupplierInfoResource {

    private final Logger log = LoggerFactory.getLogger(SupplierInfoResource.class);

    private static final String ENTITY_NAME = "supplierInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SupplierInfoService supplierInfoService;

    private final SupplierInfoRepository supplierInfoRepository;

    public SupplierInfoResource(SupplierInfoService supplierInfoService, SupplierInfoRepository supplierInfoRepository) {
        this.supplierInfoService = supplierInfoService;
        this.supplierInfoRepository = supplierInfoRepository;
    }

    /**
     * {@code POST  /supplier-infos} : Create a new supplierInfo.
     *
     * @param supplierInfo the supplierInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new supplierInfo, or with status {@code 400 (Bad Request)} if the supplierInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/supplier-infos")
    public ResponseEntity<SupplierInfo> createSupplierInfo(@RequestBody SupplierInfo supplierInfo) throws URISyntaxException {
        log.debug("REST request to save SupplierInfo : {}", supplierInfo);
        if (supplierInfo.getId() != null) {
            throw new BadRequestAlertException("A new supplierInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SupplierInfo result = supplierInfoService.save(supplierInfo);
        return ResponseEntity
            .created(new URI("/api/supplier-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /supplier-infos/:id} : Updates an existing supplierInfo.
     *
     * @param id the id of the supplierInfo to save.
     * @param supplierInfo the supplierInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supplierInfo,
     * or with status {@code 400 (Bad Request)} if the supplierInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the supplierInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/supplier-infos/{id}")
    public ResponseEntity<SupplierInfo> updateSupplierInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SupplierInfo supplierInfo
    ) throws URISyntaxException {
        log.debug("REST request to update SupplierInfo : {}, {}", id, supplierInfo);
        if (supplierInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, supplierInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!supplierInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SupplierInfo result = supplierInfoService.save(supplierInfo);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, supplierInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /supplier-infos/:id} : Partial updates given fields of an existing supplierInfo, field will ignore if it is null
     *
     * @param id the id of the supplierInfo to save.
     * @param supplierInfo the supplierInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supplierInfo,
     * or with status {@code 400 (Bad Request)} if the supplierInfo is not valid,
     * or with status {@code 404 (Not Found)} if the supplierInfo is not found,
     * or with status {@code 500 (Internal Server Error)} if the supplierInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/supplier-infos/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<SupplierInfo> partialUpdateSupplierInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SupplierInfo supplierInfo
    ) throws URISyntaxException {
        log.debug("REST request to partial update SupplierInfo partially : {}, {}", id, supplierInfo);
        if (supplierInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, supplierInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!supplierInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SupplierInfo> result = supplierInfoService.partialUpdate(supplierInfo);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, supplierInfo.getId().toString())
        );
    }

    /**
     * {@code GET  /supplier-infos} : get all the supplierInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of supplierInfos in body.
     */
    @GetMapping("/supplier-infos")
    public List<SupplierInfo> getAllSupplierInfos() {
        log.debug("REST request to get all SupplierInfos");
        return supplierInfoService.findAll();
    }

    /**
     * {@code GET  /supplier-infos/:id} : get the "id" supplierInfo.
     *
     * @param id the id of the supplierInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the supplierInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/supplier-infos/{id}")
    public ResponseEntity<SupplierInfo> getSupplierInfo(@PathVariable Long id) {
        log.debug("REST request to get SupplierInfo : {}", id);
        Optional<SupplierInfo> supplierInfo = supplierInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(supplierInfo);
    }

    /**
     * {@code DELETE  /supplier-infos/:id} : delete the "id" supplierInfo.
     *
     * @param id the id of the supplierInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/supplier-infos/{id}")
    public ResponseEntity<Void> deleteSupplierInfo(@PathVariable Long id) {
        log.debug("REST request to delete SupplierInfo : {}", id);
        supplierInfoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
