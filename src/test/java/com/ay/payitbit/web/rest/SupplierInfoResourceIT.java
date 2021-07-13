package com.ay.payitbit.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ay.payitbit.IntegrationTest;
import com.ay.payitbit.domain.SupplierInfo;
import com.ay.payitbit.repository.SupplierInfoRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SupplierInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SupplierInfoResourceIT {

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/supplier-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SupplierInfoRepository supplierInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSupplierInfoMockMvc;

    private SupplierInfo supplierInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SupplierInfo createEntity(EntityManager em) {
        SupplierInfo supplierInfo = new SupplierInfo()
            .companyName(DEFAULT_COMPANY_NAME)
            .number(DEFAULT_NUMBER)
            .address(DEFAULT_ADDRESS)
            .website(DEFAULT_WEBSITE);
        return supplierInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SupplierInfo createUpdatedEntity(EntityManager em) {
        SupplierInfo supplierInfo = new SupplierInfo()
            .companyName(UPDATED_COMPANY_NAME)
            .number(UPDATED_NUMBER)
            .address(UPDATED_ADDRESS)
            .website(UPDATED_WEBSITE);
        return supplierInfo;
    }

    @BeforeEach
    public void initTest() {
        supplierInfo = createEntity(em);
    }

    @Test
    @Transactional
    void createSupplierInfo() throws Exception {
        int databaseSizeBeforeCreate = supplierInfoRepository.findAll().size();
        // Create the SupplierInfo
        restSupplierInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(supplierInfo)))
            .andExpect(status().isCreated());

        // Validate the SupplierInfo in the database
        List<SupplierInfo> supplierInfoList = supplierInfoRepository.findAll();
        assertThat(supplierInfoList).hasSize(databaseSizeBeforeCreate + 1);
        SupplierInfo testSupplierInfo = supplierInfoList.get(supplierInfoList.size() - 1);
        assertThat(testSupplierInfo.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testSupplierInfo.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testSupplierInfo.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testSupplierInfo.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
    }

    @Test
    @Transactional
    void createSupplierInfoWithExistingId() throws Exception {
        // Create the SupplierInfo with an existing ID
        supplierInfo.setId(1L);

        int databaseSizeBeforeCreate = supplierInfoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSupplierInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(supplierInfo)))
            .andExpect(status().isBadRequest());

        // Validate the SupplierInfo in the database
        List<SupplierInfo> supplierInfoList = supplierInfoRepository.findAll();
        assertThat(supplierInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSupplierInfos() throws Exception {
        // Initialize the database
        supplierInfoRepository.saveAndFlush(supplierInfo);

        // Get all the supplierInfoList
        restSupplierInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(supplierInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)));
    }

    @Test
    @Transactional
    void getSupplierInfo() throws Exception {
        // Initialize the database
        supplierInfoRepository.saveAndFlush(supplierInfo);

        // Get the supplierInfo
        restSupplierInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, supplierInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(supplierInfo.getId().intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE));
    }

    @Test
    @Transactional
    void getNonExistingSupplierInfo() throws Exception {
        // Get the supplierInfo
        restSupplierInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSupplierInfo() throws Exception {
        // Initialize the database
        supplierInfoRepository.saveAndFlush(supplierInfo);

        int databaseSizeBeforeUpdate = supplierInfoRepository.findAll().size();

        // Update the supplierInfo
        SupplierInfo updatedSupplierInfo = supplierInfoRepository.findById(supplierInfo.getId()).get();
        // Disconnect from session so that the updates on updatedSupplierInfo are not directly saved in db
        em.detach(updatedSupplierInfo);
        updatedSupplierInfo.companyName(UPDATED_COMPANY_NAME).number(UPDATED_NUMBER).address(UPDATED_ADDRESS).website(UPDATED_WEBSITE);

        restSupplierInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSupplierInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSupplierInfo))
            )
            .andExpect(status().isOk());

        // Validate the SupplierInfo in the database
        List<SupplierInfo> supplierInfoList = supplierInfoRepository.findAll();
        assertThat(supplierInfoList).hasSize(databaseSizeBeforeUpdate);
        SupplierInfo testSupplierInfo = supplierInfoList.get(supplierInfoList.size() - 1);
        assertThat(testSupplierInfo.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testSupplierInfo.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testSupplierInfo.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSupplierInfo.getWebsite()).isEqualTo(UPDATED_WEBSITE);
    }

    @Test
    @Transactional
    void putNonExistingSupplierInfo() throws Exception {
        int databaseSizeBeforeUpdate = supplierInfoRepository.findAll().size();
        supplierInfo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSupplierInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, supplierInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(supplierInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the SupplierInfo in the database
        List<SupplierInfo> supplierInfoList = supplierInfoRepository.findAll();
        assertThat(supplierInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSupplierInfo() throws Exception {
        int databaseSizeBeforeUpdate = supplierInfoRepository.findAll().size();
        supplierInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSupplierInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(supplierInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the SupplierInfo in the database
        List<SupplierInfo> supplierInfoList = supplierInfoRepository.findAll();
        assertThat(supplierInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSupplierInfo() throws Exception {
        int databaseSizeBeforeUpdate = supplierInfoRepository.findAll().size();
        supplierInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSupplierInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(supplierInfo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SupplierInfo in the database
        List<SupplierInfo> supplierInfoList = supplierInfoRepository.findAll();
        assertThat(supplierInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSupplierInfoWithPatch() throws Exception {
        // Initialize the database
        supplierInfoRepository.saveAndFlush(supplierInfo);

        int databaseSizeBeforeUpdate = supplierInfoRepository.findAll().size();

        // Update the supplierInfo using partial update
        SupplierInfo partialUpdatedSupplierInfo = new SupplierInfo();
        partialUpdatedSupplierInfo.setId(supplierInfo.getId());

        partialUpdatedSupplierInfo.address(UPDATED_ADDRESS).website(UPDATED_WEBSITE);

        restSupplierInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSupplierInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSupplierInfo))
            )
            .andExpect(status().isOk());

        // Validate the SupplierInfo in the database
        List<SupplierInfo> supplierInfoList = supplierInfoRepository.findAll();
        assertThat(supplierInfoList).hasSize(databaseSizeBeforeUpdate);
        SupplierInfo testSupplierInfo = supplierInfoList.get(supplierInfoList.size() - 1);
        assertThat(testSupplierInfo.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testSupplierInfo.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testSupplierInfo.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSupplierInfo.getWebsite()).isEqualTo(UPDATED_WEBSITE);
    }

    @Test
    @Transactional
    void fullUpdateSupplierInfoWithPatch() throws Exception {
        // Initialize the database
        supplierInfoRepository.saveAndFlush(supplierInfo);

        int databaseSizeBeforeUpdate = supplierInfoRepository.findAll().size();

        // Update the supplierInfo using partial update
        SupplierInfo partialUpdatedSupplierInfo = new SupplierInfo();
        partialUpdatedSupplierInfo.setId(supplierInfo.getId());

        partialUpdatedSupplierInfo
            .companyName(UPDATED_COMPANY_NAME)
            .number(UPDATED_NUMBER)
            .address(UPDATED_ADDRESS)
            .website(UPDATED_WEBSITE);

        restSupplierInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSupplierInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSupplierInfo))
            )
            .andExpect(status().isOk());

        // Validate the SupplierInfo in the database
        List<SupplierInfo> supplierInfoList = supplierInfoRepository.findAll();
        assertThat(supplierInfoList).hasSize(databaseSizeBeforeUpdate);
        SupplierInfo testSupplierInfo = supplierInfoList.get(supplierInfoList.size() - 1);
        assertThat(testSupplierInfo.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testSupplierInfo.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testSupplierInfo.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSupplierInfo.getWebsite()).isEqualTo(UPDATED_WEBSITE);
    }

    @Test
    @Transactional
    void patchNonExistingSupplierInfo() throws Exception {
        int databaseSizeBeforeUpdate = supplierInfoRepository.findAll().size();
        supplierInfo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSupplierInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, supplierInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(supplierInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the SupplierInfo in the database
        List<SupplierInfo> supplierInfoList = supplierInfoRepository.findAll();
        assertThat(supplierInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSupplierInfo() throws Exception {
        int databaseSizeBeforeUpdate = supplierInfoRepository.findAll().size();
        supplierInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSupplierInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(supplierInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the SupplierInfo in the database
        List<SupplierInfo> supplierInfoList = supplierInfoRepository.findAll();
        assertThat(supplierInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSupplierInfo() throws Exception {
        int databaseSizeBeforeUpdate = supplierInfoRepository.findAll().size();
        supplierInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSupplierInfoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(supplierInfo))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SupplierInfo in the database
        List<SupplierInfo> supplierInfoList = supplierInfoRepository.findAll();
        assertThat(supplierInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSupplierInfo() throws Exception {
        // Initialize the database
        supplierInfoRepository.saveAndFlush(supplierInfo);

        int databaseSizeBeforeDelete = supplierInfoRepository.findAll().size();

        // Delete the supplierInfo
        restSupplierInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, supplierInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SupplierInfo> supplierInfoList = supplierInfoRepository.findAll();
        assertThat(supplierInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
