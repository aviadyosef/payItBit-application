package com.ay.payitbit.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ay.payitbit.IntegrationTest;
import com.ay.payitbit.domain.UserInfo;
import com.ay.payitbit.repository.UserInfoRepository;
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
 * Integration tests for the {@link UserInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UserInfoResourceIT {

    private static final Long DEFAULT_SEQ_NO = 1L;
    private static final Long UPDATED_SEQ_NO = 2L;

    private static final Long DEFAULT_CREATED_ON = 1L;
    private static final Long UPDATED_CREATED_ON = 2L;

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "aviad.yosef@XXX.com";
    private static final String UPDATED_EMAIL = "BBBBBB@BBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TELEGRAM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TELEGRAM_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/user-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserInfoMockMvc;

    private UserInfo userInfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserInfo createEntity(EntityManager em) {
        UserInfo userInfo = new UserInfo()
            .seqNo(DEFAULT_SEQ_NO)
            .createdOn(DEFAULT_CREATED_ON)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .companyName(DEFAULT_COMPANY_NAME)
            .telegramName(DEFAULT_TELEGRAM_NAME);
        return userInfo;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserInfo createUpdatedEntity(EntityManager em) {
        UserInfo userInfo = new UserInfo()
            .seqNo(UPDATED_SEQ_NO)
            .createdOn(UPDATED_CREATED_ON)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .companyName(UPDATED_COMPANY_NAME)
            .telegramName(UPDATED_TELEGRAM_NAME);
        return userInfo;
    }

    @BeforeEach
    public void initTest() {
        userInfo = createEntity(em);
    }

    @Test
    @Transactional
    void createUserInfo() throws Exception {
        int databaseSizeBeforeCreate = userInfoRepository.findAll().size();
        // Create the UserInfo
        restUserInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isCreated());

        // Validate the UserInfo in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeCreate + 1);
        UserInfo testUserInfo = userInfoList.get(userInfoList.size() - 1);
        assertThat(testUserInfo.getSeqNo()).isEqualTo(DEFAULT_SEQ_NO);
        assertThat(testUserInfo.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testUserInfo.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testUserInfo.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testUserInfo.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUserInfo.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testUserInfo.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testUserInfo.getTelegramName()).isEqualTo(DEFAULT_TELEGRAM_NAME);
    }

    @Test
    @Transactional
    void createUserInfoWithExistingId() throws Exception {
        // Create the UserInfo with an existing ID
        userInfo.setId(1L);

        int databaseSizeBeforeCreate = userInfoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserInfoMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isBadRequest());

        // Validate the UserInfo in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUserInfos() throws Exception {
        // Initialize the database
        userInfoRepository.saveAndFlush(userInfo);

        // Get all the userInfoList
        restUserInfoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].seqNo").value(hasItem(DEFAULT_SEQ_NO.intValue())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].telegramName").value(hasItem(DEFAULT_TELEGRAM_NAME)));
    }

    @Test
    @Transactional
    void getUserInfo() throws Exception {
        // Initialize the database
        userInfoRepository.saveAndFlush(userInfo);

        // Get the userInfo
        restUserInfoMockMvc
            .perform(get(ENTITY_API_URL_ID, userInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userInfo.getId().intValue()))
            .andExpect(jsonPath("$.seqNo").value(DEFAULT_SEQ_NO.intValue()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME))
            .andExpect(jsonPath("$.telegramName").value(DEFAULT_TELEGRAM_NAME));
    }

    @Test
    @Transactional
    void getNonExistingUserInfo() throws Exception {
        // Get the userInfo
        restUserInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewUserInfo() throws Exception {
        // Initialize the database
        userInfoRepository.saveAndFlush(userInfo);

        int databaseSizeBeforeUpdate = userInfoRepository.findAll().size();

        // Update the userInfo
        UserInfo updatedUserInfo = userInfoRepository.findById(userInfo.getId()).get();
        // Disconnect from session so that the updates on updatedUserInfo are not directly saved in db
        em.detach(updatedUserInfo);
        updatedUserInfo
            .seqNo(UPDATED_SEQ_NO)
            .createdOn(UPDATED_CREATED_ON)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .companyName(UPDATED_COMPANY_NAME)
            .telegramName(UPDATED_TELEGRAM_NAME);

        restUserInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUserInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedUserInfo))
            )
            .andExpect(status().isOk());

        // Validate the UserInfo in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeUpdate);
        UserInfo testUserInfo = userInfoList.get(userInfoList.size() - 1);
        assertThat(testUserInfo.getSeqNo()).isEqualTo(UPDATED_SEQ_NO);
        assertThat(testUserInfo.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testUserInfo.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testUserInfo.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testUserInfo.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserInfo.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testUserInfo.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testUserInfo.getTelegramName()).isEqualTo(UPDATED_TELEGRAM_NAME);
    }

    @Test
    @Transactional
    void putNonExistingUserInfo() throws Exception {
        int databaseSizeBeforeUpdate = userInfoRepository.findAll().size();
        userInfo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, userInfo.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserInfo in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUserInfo() throws Exception {
        int databaseSizeBeforeUpdate = userInfoRepository.findAll().size();
        userInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserInfoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserInfo in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUserInfo() throws Exception {
        int databaseSizeBeforeUpdate = userInfoRepository.findAll().size();
        userInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserInfoMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserInfo in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUserInfoWithPatch() throws Exception {
        // Initialize the database
        userInfoRepository.saveAndFlush(userInfo);

        int databaseSizeBeforeUpdate = userInfoRepository.findAll().size();

        // Update the userInfo using partial update
        UserInfo partialUpdatedUserInfo = new UserInfo();
        partialUpdatedUserInfo.setId(userInfo.getId());

        partialUpdatedUserInfo
            .createdOn(UPDATED_CREATED_ON)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .telegramName(UPDATED_TELEGRAM_NAME);

        restUserInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserInfo))
            )
            .andExpect(status().isOk());

        // Validate the UserInfo in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeUpdate);
        UserInfo testUserInfo = userInfoList.get(userInfoList.size() - 1);
        assertThat(testUserInfo.getSeqNo()).isEqualTo(DEFAULT_SEQ_NO);
        assertThat(testUserInfo.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testUserInfo.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testUserInfo.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testUserInfo.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUserInfo.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testUserInfo.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testUserInfo.getTelegramName()).isEqualTo(UPDATED_TELEGRAM_NAME);
    }

    @Test
    @Transactional
    void fullUpdateUserInfoWithPatch() throws Exception {
        // Initialize the database
        userInfoRepository.saveAndFlush(userInfo);

        int databaseSizeBeforeUpdate = userInfoRepository.findAll().size();

        // Update the userInfo using partial update
        UserInfo partialUpdatedUserInfo = new UserInfo();
        partialUpdatedUserInfo.setId(userInfo.getId());

        partialUpdatedUserInfo
            .seqNo(UPDATED_SEQ_NO)
            .createdOn(UPDATED_CREATED_ON)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .companyName(UPDATED_COMPANY_NAME)
            .telegramName(UPDATED_TELEGRAM_NAME);

        restUserInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUserInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserInfo))
            )
            .andExpect(status().isOk());

        // Validate the UserInfo in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeUpdate);
        UserInfo testUserInfo = userInfoList.get(userInfoList.size() - 1);
        assertThat(testUserInfo.getSeqNo()).isEqualTo(UPDATED_SEQ_NO);
        assertThat(testUserInfo.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testUserInfo.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testUserInfo.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testUserInfo.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserInfo.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testUserInfo.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testUserInfo.getTelegramName()).isEqualTo(UPDATED_TELEGRAM_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingUserInfo() throws Exception {
        int databaseSizeBeforeUpdate = userInfoRepository.findAll().size();
        userInfo.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, userInfo.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserInfo in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUserInfo() throws Exception {
        int databaseSizeBeforeUpdate = userInfoRepository.findAll().size();
        userInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserInfoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(userInfo))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserInfo in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUserInfo() throws Exception {
        int databaseSizeBeforeUpdate = userInfoRepository.findAll().size();
        userInfo.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUserInfoMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(userInfo)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UserInfo in the database
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUserInfo() throws Exception {
        // Initialize the database
        userInfoRepository.saveAndFlush(userInfo);

        int databaseSizeBeforeDelete = userInfoRepository.findAll().size();

        // Delete the userInfo
        restUserInfoMockMvc
            .perform(delete(ENTITY_API_URL_ID, userInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        assertThat(userInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
