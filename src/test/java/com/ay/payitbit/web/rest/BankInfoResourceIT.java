package com.ay.payitbit.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ay.payitbit.IntegrationTest;
import com.ay.payitbit.domain.BankInfo;
import com.ay.payitbit.repository.BankInfoRepository;
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
 * Integration tests for the {@link BankInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BankInfoResourceIT {

    private static final String DEFAULT_ACCOUNT_HOLDER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_HOLDER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_IBAN_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_IBAN_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SWIFT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SWIFT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE_NO = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_NO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bank-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BankInfoRepository bankInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBankInfoMockMvc;

    private BankInfo bankInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankInfo createEntity(EntityManager em) {
        BankInfo bankInfo = new BankInfo()
            .accountHolderName(DEFAULT_ACCOUNT_HOLDER_NAME)
            .accountNumber(DEFAULT_ACCOUNT_NUMBER)
            .ibanNumber(DEFAULT_IBAN_NUMBER)
            .swiftCode(DEFAULT_SWIFT_CODE)
            .bankAddress(DEFAULT_BANK_ADDRESS)
            .country(DEFAULT_COUNTRY)
            .referenceNo(DEFAULT_REFERENCE_NO);
        return bankInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankInfo createUpdatedEntity(EntityManager em) {
        BankInfo bankInfo = new BankInfo()
            .accountHolderName(UPDATED_ACCOUNT_HOLDER_NAME)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .ibanNumber(UPDATED_IBAN_NUMBER)
            .swiftCode(UPDATED_SWIFT_CODE)
            .bankAddress(UPDATED_BANK_ADDRESS)
            .country(UPDATED_COUNTRY)
            .referenceNo(UPDATED_REFERENCE_NO);
        return bankInfo;
    }

    @BeforeEach
    public void initTest() {
        bankInfo = createEntity(em);
    }

    @Test
    @Transactional
    void createBankInfo() throws Exception {
        int databaseSizeBeforeCreate = bankInfoRepository.findAll().size();
        // Create the BankInfo
        restBankInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bankInfo)))
            .andExpect(status().isCreated());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeCreate + 1);
        BankInfo testBankInfo = bankInfoList.get(bankInfoList.size() - 1);
        assertThat(testBankInfo.getAccountHolderName()).isEqualTo(DEFAULT_ACCOUNT_HOLDER_NAME);
        assertThat(testBankInfo.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testBankInfo.getIbanNumber()).isEqualTo(DEFAULT_IBAN_NUMBER);
        assertThat(testBankInfo.getSwiftCode()).isEqualTo(DEFAULT_SWIFT_CODE);
        assertThat(testBankInfo.getBankAddress()).isEqualTo(DEFAULT_BANK_ADDRESS);
        assertThat(testBankInfo.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testBankInfo.getReferenceNo()).isEqualTo(DEFAULT_REFERENCE_NO);
    }

    @Test
    @Transactional
    void createBankInfoWithExistingId() throws Exception {
        // Create the BankInfo with an existing ID
        bankInfo.setId(1L);

        int databaseSizeBeforeCreate = bankInfoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bankInfo)))
            .andExpect(status().isBadRequest());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBankInfos() throws Exception {
        // Initialize the database
        bankInfoRepository.saveAndFlush(bankInfo);

        // Get all the bankInfoList
        restBankInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bankInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountHolderName").value(hasItem(DEFAULT_ACCOUNT_HOLDER_NAME)))
            .andExpect(jsonPath("$.[*].accountNumber").value(hasItem(DEFAULT_ACCOUNT_NUMBER)))
            .andExpect(jsonPath("$.[*].ibanNumber").value(hasItem(DEFAULT_IBAN_NUMBER)))
            .andExpect(jsonPath("$.[*].swiftCode").value(hasItem(DEFAULT_SWIFT_CODE)))
            .andExpect(jsonPath("$.[*].bankAddress").value(hasItem(DEFAULT_BANK_ADDRESS)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].referenceNo").value(hasItem(DEFAULT_REFERENCE_NO)));
    }

    @Test
    @Transactional
    void getBankInfo() throws Exception {
        // Initialize the database
        bankInfoRepository.saveAndFlush(bankInfo);

        // Get the bankInfo
        restBankInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, bankInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bankInfo.getId().intValue()))
            .andExpect(jsonPath("$.accountHolderName").value(DEFAULT_ACCOUNT_HOLDER_NAME))
            .andExpect(jsonPath("$.accountNumber").value(DEFAULT_ACCOUNT_NUMBER))
            .andExpect(jsonPath("$.ibanNumber").value(DEFAULT_IBAN_NUMBER))
            .andExpect(jsonPath("$.swiftCode").value(DEFAULT_SWIFT_CODE))
            .andExpect(jsonPath("$.bankAddress").value(DEFAULT_BANK_ADDRESS))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.referenceNo").value(DEFAULT_REFERENCE_NO));
    }

    @Test
    @Transactional
    void getNonExistingBankInfo() throws Exception {
        // Get the bankInfo
        restBankInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBankInfo() throws Exception {
        // Initialize the database
        bankInfoRepository.saveAndFlush(bankInfo);

        int databaseSizeBeforeUpdate = bankInfoRepository.findAll().size();

        // Update the bankInfo
        BankInfo updatedBankInfo = bankInfoRepository.findById(bankInfo.getId()).get();
        // Disconnect from session so that the updates on updatedBankInfo are not directly saved in db
        em.detach(updatedBankInfo);
        updatedBankInfo
            .accountHolderName(UPDATED_ACCOUNT_HOLDER_NAME)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .ibanNumber(UPDATED_IBAN_NUMBER)
            .swiftCode(UPDATED_SWIFT_CODE)
            .bankAddress(UPDATED_BANK_ADDRESS)
            .country(UPDATED_COUNTRY)
            .referenceNo(UPDATED_REFERENCE_NO);

        restBankInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBankInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBankInfo))
            )
            .andExpect(status().isOk());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeUpdate);
        BankInfo testBankInfo = bankInfoList.get(bankInfoList.size() - 1);
        assertThat(testBankInfo.getAccountHolderName()).isEqualTo(UPDATED_ACCOUNT_HOLDER_NAME);
        assertThat(testBankInfo.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testBankInfo.getIbanNumber()).isEqualTo(UPDATED_IBAN_NUMBER);
        assertThat(testBankInfo.getSwiftCode()).isEqualTo(UPDATED_SWIFT_CODE);
        assertThat(testBankInfo.getBankAddress()).isEqualTo(UPDATED_BANK_ADDRESS);
        assertThat(testBankInfo.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testBankInfo.getReferenceNo()).isEqualTo(UPDATED_REFERENCE_NO);
    }

    @Test
    @Transactional
    void putNonExistingBankInfo() throws Exception {
        int databaseSizeBeforeUpdate = bankInfoRepository.findAll().size();
        bankInfo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bankInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bankInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBankInfo() throws Exception {
        int databaseSizeBeforeUpdate = bankInfoRepository.findAll().size();
        bankInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bankInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBankInfo() throws Exception {
        int databaseSizeBeforeUpdate = bankInfoRepository.findAll().size();
        bankInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bankInfo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBankInfoWithPatch() throws Exception {
        // Initialize the database
        bankInfoRepository.saveAndFlush(bankInfo);

        int databaseSizeBeforeUpdate = bankInfoRepository.findAll().size();

        // Update the bankInfo using partial update
        BankInfo partialUpdatedBankInfo = new BankInfo();
        partialUpdatedBankInfo.setId(bankInfo.getId());

        partialUpdatedBankInfo.accountHolderName(UPDATED_ACCOUNT_HOLDER_NAME).country(UPDATED_COUNTRY).referenceNo(UPDATED_REFERENCE_NO);

        restBankInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBankInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBankInfo))
            )
            .andExpect(status().isOk());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeUpdate);
        BankInfo testBankInfo = bankInfoList.get(bankInfoList.size() - 1);
        assertThat(testBankInfo.getAccountHolderName()).isEqualTo(UPDATED_ACCOUNT_HOLDER_NAME);
        assertThat(testBankInfo.getAccountNumber()).isEqualTo(DEFAULT_ACCOUNT_NUMBER);
        assertThat(testBankInfo.getIbanNumber()).isEqualTo(DEFAULT_IBAN_NUMBER);
        assertThat(testBankInfo.getSwiftCode()).isEqualTo(DEFAULT_SWIFT_CODE);
        assertThat(testBankInfo.getBankAddress()).isEqualTo(DEFAULT_BANK_ADDRESS);
        assertThat(testBankInfo.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testBankInfo.getReferenceNo()).isEqualTo(UPDATED_REFERENCE_NO);
    }

    @Test
    @Transactional
    void fullUpdateBankInfoWithPatch() throws Exception {
        // Initialize the database
        bankInfoRepository.saveAndFlush(bankInfo);

        int databaseSizeBeforeUpdate = bankInfoRepository.findAll().size();

        // Update the bankInfo using partial update
        BankInfo partialUpdatedBankInfo = new BankInfo();
        partialUpdatedBankInfo.setId(bankInfo.getId());

        partialUpdatedBankInfo
            .accountHolderName(UPDATED_ACCOUNT_HOLDER_NAME)
            .accountNumber(UPDATED_ACCOUNT_NUMBER)
            .ibanNumber(UPDATED_IBAN_NUMBER)
            .swiftCode(UPDATED_SWIFT_CODE)
            .bankAddress(UPDATED_BANK_ADDRESS)
            .country(UPDATED_COUNTRY)
            .referenceNo(UPDATED_REFERENCE_NO);

        restBankInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBankInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBankInfo))
            )
            .andExpect(status().isOk());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeUpdate);
        BankInfo testBankInfo = bankInfoList.get(bankInfoList.size() - 1);
        assertThat(testBankInfo.getAccountHolderName()).isEqualTo(UPDATED_ACCOUNT_HOLDER_NAME);
        assertThat(testBankInfo.getAccountNumber()).isEqualTo(UPDATED_ACCOUNT_NUMBER);
        assertThat(testBankInfo.getIbanNumber()).isEqualTo(UPDATED_IBAN_NUMBER);
        assertThat(testBankInfo.getSwiftCode()).isEqualTo(UPDATED_SWIFT_CODE);
        assertThat(testBankInfo.getBankAddress()).isEqualTo(UPDATED_BANK_ADDRESS);
        assertThat(testBankInfo.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testBankInfo.getReferenceNo()).isEqualTo(UPDATED_REFERENCE_NO);
    }

    @Test
    @Transactional
    void patchNonExistingBankInfo() throws Exception {
        int databaseSizeBeforeUpdate = bankInfoRepository.findAll().size();
        bankInfo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bankInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bankInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBankInfo() throws Exception {
        int databaseSizeBeforeUpdate = bankInfoRepository.findAll().size();
        bankInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bankInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBankInfo() throws Exception {
        int databaseSizeBeforeUpdate = bankInfoRepository.findAll().size();
        bankInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankInfoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bankInfo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BankInfo in the database
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBankInfo() throws Exception {
        // Initialize the database
        bankInfoRepository.saveAndFlush(bankInfo);

        int databaseSizeBeforeDelete = bankInfoRepository.findAll().size();

        // Delete the bankInfo
        restBankInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, bankInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BankInfo> bankInfoList = bankInfoRepository.findAll();
        assertThat(bankInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
