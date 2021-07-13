package com.ay.payitbit.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ay.payitbit.IntegrationTest;
import com.ay.payitbit.domain.InvoiceInfo;
import com.ay.payitbit.repository.InvoiceInfoRepository;
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
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link InvoiceInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InvoiceInfoResourceIT {

    private static final String DEFAULT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_INVOICE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final byte[] DEFAULT_INVOICE_PICTURE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_INVOICE_PICTURE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_INVOICE_PICTURE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_INVOICE_PICTURE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_AGREEMENT = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_AGREEMENT = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_AGREEMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_AGREEMENT_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_SERVICE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/invoice-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InvoiceInfoRepository invoiceInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInvoiceInfoMockMvc;

    private InvoiceInfo invoiceInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceInfo createEntity(EntityManager em) {
        InvoiceInfo invoiceInfo = new InvoiceInfo()
            .amount(DEFAULT_AMOUNT)
            .invoiceNumber(DEFAULT_INVOICE_NUMBER)
            .currency(DEFAULT_CURRENCY)
            .invoicePicture(DEFAULT_INVOICE_PICTURE)
            .invoicePictureContentType(DEFAULT_INVOICE_PICTURE_CONTENT_TYPE)
            .agreement(DEFAULT_AGREEMENT)
            .agreementContentType(DEFAULT_AGREEMENT_CONTENT_TYPE)
            .serviceDescription(DEFAULT_SERVICE_DESCRIPTION);
        return invoiceInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvoiceInfo createUpdatedEntity(EntityManager em) {
        InvoiceInfo invoiceInfo = new InvoiceInfo()
            .amount(UPDATED_AMOUNT)
            .invoiceNumber(UPDATED_INVOICE_NUMBER)
            .currency(UPDATED_CURRENCY)
            .invoicePicture(UPDATED_INVOICE_PICTURE)
            .invoicePictureContentType(UPDATED_INVOICE_PICTURE_CONTENT_TYPE)
            .agreement(UPDATED_AGREEMENT)
            .agreementContentType(UPDATED_AGREEMENT_CONTENT_TYPE)
            .serviceDescription(UPDATED_SERVICE_DESCRIPTION);
        return invoiceInfo;
    }

    @BeforeEach
    public void initTest() {
        invoiceInfo = createEntity(em);
    }

    @Test
    @Transactional
    void createInvoiceInfo() throws Exception {
        int databaseSizeBeforeCreate = invoiceInfoRepository.findAll().size();
        // Create the InvoiceInfo
        restInvoiceInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(invoiceInfo)))
            .andExpect(status().isCreated());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeCreate + 1);
        InvoiceInfo testInvoiceInfo = invoiceInfoList.get(invoiceInfoList.size() - 1);
        assertThat(testInvoiceInfo.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testInvoiceInfo.getInvoiceNumber()).isEqualTo(DEFAULT_INVOICE_NUMBER);
        assertThat(testInvoiceInfo.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testInvoiceInfo.getInvoicePicture()).isEqualTo(DEFAULT_INVOICE_PICTURE);
        assertThat(testInvoiceInfo.getInvoicePictureContentType()).isEqualTo(DEFAULT_INVOICE_PICTURE_CONTENT_TYPE);
        assertThat(testInvoiceInfo.getAgreement()).isEqualTo(DEFAULT_AGREEMENT);
        assertThat(testInvoiceInfo.getAgreementContentType()).isEqualTo(DEFAULT_AGREEMENT_CONTENT_TYPE);
        assertThat(testInvoiceInfo.getServiceDescription()).isEqualTo(DEFAULT_SERVICE_DESCRIPTION);
    }

    @Test
    @Transactional
    void createInvoiceInfoWithExistingId() throws Exception {
        // Create the InvoiceInfo with an existing ID
        invoiceInfo.setId(1L);

        int databaseSizeBeforeCreate = invoiceInfoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvoiceInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(invoiceInfo)))
            .andExpect(status().isBadRequest());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInvoiceInfos() throws Exception {
        // Initialize the database
        invoiceInfoRepository.saveAndFlush(invoiceInfo);

        // Get all the invoiceInfoList
        restInvoiceInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(invoiceInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.[*].invoiceNumber").value(hasItem(DEFAULT_INVOICE_NUMBER)))
            .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY)))
            .andExpect(jsonPath("$.[*].invoicePictureContentType").value(hasItem(DEFAULT_INVOICE_PICTURE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].invoicePicture").value(hasItem(Base64Utils.encodeToString(DEFAULT_INVOICE_PICTURE))))
            .andExpect(jsonPath("$.[*].agreementContentType").value(hasItem(DEFAULT_AGREEMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].agreement").value(hasItem(Base64Utils.encodeToString(DEFAULT_AGREEMENT))))
            .andExpect(jsonPath("$.[*].serviceDescription").value(hasItem(DEFAULT_SERVICE_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getInvoiceInfo() throws Exception {
        // Initialize the database
        invoiceInfoRepository.saveAndFlush(invoiceInfo);

        // Get the invoiceInfo
        restInvoiceInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, invoiceInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(invoiceInfo.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT))
            .andExpect(jsonPath("$.invoiceNumber").value(DEFAULT_INVOICE_NUMBER))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY))
            .andExpect(jsonPath("$.invoicePictureContentType").value(DEFAULT_INVOICE_PICTURE_CONTENT_TYPE))
            .andExpect(jsonPath("$.invoicePicture").value(Base64Utils.encodeToString(DEFAULT_INVOICE_PICTURE)))
            .andExpect(jsonPath("$.agreementContentType").value(DEFAULT_AGREEMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.agreement").value(Base64Utils.encodeToString(DEFAULT_AGREEMENT)))
            .andExpect(jsonPath("$.serviceDescription").value(DEFAULT_SERVICE_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingInvoiceInfo() throws Exception {
        // Get the invoiceInfo
        restInvoiceInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewInvoiceInfo() throws Exception {
        // Initialize the database
        invoiceInfoRepository.saveAndFlush(invoiceInfo);

        int databaseSizeBeforeUpdate = invoiceInfoRepository.findAll().size();

        // Update the invoiceInfo
        InvoiceInfo updatedInvoiceInfo = invoiceInfoRepository.findById(invoiceInfo.getId()).get();
        // Disconnect from session so that the updates on updatedInvoiceInfo are not directly saved in db
        em.detach(updatedInvoiceInfo);
        updatedInvoiceInfo
            .amount(UPDATED_AMOUNT)
            .invoiceNumber(UPDATED_INVOICE_NUMBER)
            .currency(UPDATED_CURRENCY)
            .invoicePicture(UPDATED_INVOICE_PICTURE)
            .invoicePictureContentType(UPDATED_INVOICE_PICTURE_CONTENT_TYPE)
            .agreement(UPDATED_AGREEMENT)
            .agreementContentType(UPDATED_AGREEMENT_CONTENT_TYPE)
            .serviceDescription(UPDATED_SERVICE_DESCRIPTION);

        restInvoiceInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedInvoiceInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedInvoiceInfo))
            )
            .andExpect(status().isOk());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeUpdate);
        InvoiceInfo testInvoiceInfo = invoiceInfoList.get(invoiceInfoList.size() - 1);
        assertThat(testInvoiceInfo.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testInvoiceInfo.getInvoiceNumber()).isEqualTo(UPDATED_INVOICE_NUMBER);
        assertThat(testInvoiceInfo.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testInvoiceInfo.getInvoicePicture()).isEqualTo(UPDATED_INVOICE_PICTURE);
        assertThat(testInvoiceInfo.getInvoicePictureContentType()).isEqualTo(UPDATED_INVOICE_PICTURE_CONTENT_TYPE);
        assertThat(testInvoiceInfo.getAgreement()).isEqualTo(UPDATED_AGREEMENT);
        assertThat(testInvoiceInfo.getAgreementContentType()).isEqualTo(UPDATED_AGREEMENT_CONTENT_TYPE);
        assertThat(testInvoiceInfo.getServiceDescription()).isEqualTo(UPDATED_SERVICE_DESCRIPTION);
    }

    @Test
    @Transactional
    void putNonExistingInvoiceInfo() throws Exception {
        int databaseSizeBeforeUpdate = invoiceInfoRepository.findAll().size();
        invoiceInfo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, invoiceInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInvoiceInfo() throws Exception {
        int databaseSizeBeforeUpdate = invoiceInfoRepository.findAll().size();
        invoiceInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(invoiceInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInvoiceInfo() throws Exception {
        int databaseSizeBeforeUpdate = invoiceInfoRepository.findAll().size();
        invoiceInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(invoiceInfo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInvoiceInfoWithPatch() throws Exception {
        // Initialize the database
        invoiceInfoRepository.saveAndFlush(invoiceInfo);

        int databaseSizeBeforeUpdate = invoiceInfoRepository.findAll().size();

        // Update the invoiceInfo using partial update
        InvoiceInfo partialUpdatedInvoiceInfo = new InvoiceInfo();
        partialUpdatedInvoiceInfo.setId(invoiceInfo.getId());

        partialUpdatedInvoiceInfo
            .amount(UPDATED_AMOUNT)
            .invoiceNumber(UPDATED_INVOICE_NUMBER)
            .currency(UPDATED_CURRENCY)
            .agreement(UPDATED_AGREEMENT)
            .agreementContentType(UPDATED_AGREEMENT_CONTENT_TYPE);

        restInvoiceInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInvoiceInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceInfo))
            )
            .andExpect(status().isOk());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeUpdate);
        InvoiceInfo testInvoiceInfo = invoiceInfoList.get(invoiceInfoList.size() - 1);
        assertThat(testInvoiceInfo.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testInvoiceInfo.getInvoiceNumber()).isEqualTo(UPDATED_INVOICE_NUMBER);
        assertThat(testInvoiceInfo.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testInvoiceInfo.getInvoicePicture()).isEqualTo(DEFAULT_INVOICE_PICTURE);
        assertThat(testInvoiceInfo.getInvoicePictureContentType()).isEqualTo(DEFAULT_INVOICE_PICTURE_CONTENT_TYPE);
        assertThat(testInvoiceInfo.getAgreement()).isEqualTo(UPDATED_AGREEMENT);
        assertThat(testInvoiceInfo.getAgreementContentType()).isEqualTo(UPDATED_AGREEMENT_CONTENT_TYPE);
        assertThat(testInvoiceInfo.getServiceDescription()).isEqualTo(DEFAULT_SERVICE_DESCRIPTION);
    }

    @Test
    @Transactional
    void fullUpdateInvoiceInfoWithPatch() throws Exception {
        // Initialize the database
        invoiceInfoRepository.saveAndFlush(invoiceInfo);

        int databaseSizeBeforeUpdate = invoiceInfoRepository.findAll().size();

        // Update the invoiceInfo using partial update
        InvoiceInfo partialUpdatedInvoiceInfo = new InvoiceInfo();
        partialUpdatedInvoiceInfo.setId(invoiceInfo.getId());

        partialUpdatedInvoiceInfo
            .amount(UPDATED_AMOUNT)
            .invoiceNumber(UPDATED_INVOICE_NUMBER)
            .currency(UPDATED_CURRENCY)
            .invoicePicture(UPDATED_INVOICE_PICTURE)
            .invoicePictureContentType(UPDATED_INVOICE_PICTURE_CONTENT_TYPE)
            .agreement(UPDATED_AGREEMENT)
            .agreementContentType(UPDATED_AGREEMENT_CONTENT_TYPE)
            .serviceDescription(UPDATED_SERVICE_DESCRIPTION);

        restInvoiceInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInvoiceInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInvoiceInfo))
            )
            .andExpect(status().isOk());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeUpdate);
        InvoiceInfo testInvoiceInfo = invoiceInfoList.get(invoiceInfoList.size() - 1);
        assertThat(testInvoiceInfo.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testInvoiceInfo.getInvoiceNumber()).isEqualTo(UPDATED_INVOICE_NUMBER);
        assertThat(testInvoiceInfo.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testInvoiceInfo.getInvoicePicture()).isEqualTo(UPDATED_INVOICE_PICTURE);
        assertThat(testInvoiceInfo.getInvoicePictureContentType()).isEqualTo(UPDATED_INVOICE_PICTURE_CONTENT_TYPE);
        assertThat(testInvoiceInfo.getAgreement()).isEqualTo(UPDATED_AGREEMENT);
        assertThat(testInvoiceInfo.getAgreementContentType()).isEqualTo(UPDATED_AGREEMENT_CONTENT_TYPE);
        assertThat(testInvoiceInfo.getServiceDescription()).isEqualTo(UPDATED_SERVICE_DESCRIPTION);
    }

    @Test
    @Transactional
    void patchNonExistingInvoiceInfo() throws Exception {
        int databaseSizeBeforeUpdate = invoiceInfoRepository.findAll().size();
        invoiceInfo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvoiceInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, invoiceInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoiceInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInvoiceInfo() throws Exception {
        int databaseSizeBeforeUpdate = invoiceInfoRepository.findAll().size();
        invoiceInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(invoiceInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInvoiceInfo() throws Exception {
        int databaseSizeBeforeUpdate = invoiceInfoRepository.findAll().size();
        invoiceInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInvoiceInfoMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(invoiceInfo))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the InvoiceInfo in the database
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInvoiceInfo() throws Exception {
        // Initialize the database
        invoiceInfoRepository.saveAndFlush(invoiceInfo);

        int databaseSizeBeforeDelete = invoiceInfoRepository.findAll().size();

        // Delete the invoiceInfo
        restInvoiceInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, invoiceInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvoiceInfo> invoiceInfoList = invoiceInfoRepository.findAll();
        assertThat(invoiceInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
