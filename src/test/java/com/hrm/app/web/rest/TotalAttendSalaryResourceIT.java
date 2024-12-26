package com.hrm.app.web.rest;

import static com.hrm.app.domain.TotalAttendSalaryAsserts.*;
import static com.hrm.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrm.app.IntegrationTest;
import com.hrm.app.domain.TotalAttendSalary;
import com.hrm.app.repository.TotalAttendSalaryRepository;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TotalAttendSalaryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TotalAttendSalaryResourceIT {

    private static final Integer DEFAULT_TOTAL_ATTEND = 1;
    private static final Integer UPDATED_TOTAL_ATTEND = 2;

    private static final Integer DEFAULT_TOTAL_SALARY = 1;
    private static final Integer UPDATED_TOTAL_SALARY = 2;

    private static final String ENTITY_API_URL = "/api/total-attend-salaries";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TotalAttendSalaryRepository totalAttendSalaryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTotalAttendSalaryMockMvc;

    private TotalAttendSalary totalAttendSalary;

    private TotalAttendSalary insertedTotalAttendSalary;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TotalAttendSalary createEntity() {
        return new TotalAttendSalary().totalAttend(DEFAULT_TOTAL_ATTEND).totalSalary(DEFAULT_TOTAL_SALARY);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TotalAttendSalary createUpdatedEntity() {
        return new TotalAttendSalary().totalAttend(UPDATED_TOTAL_ATTEND).totalSalary(UPDATED_TOTAL_SALARY);
    }

    @BeforeEach
    public void initTest() {
        totalAttendSalary = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedTotalAttendSalary != null) {
            totalAttendSalaryRepository.delete(insertedTotalAttendSalary);
            insertedTotalAttendSalary = null;
        }
    }

    @Test
    @Transactional
    void createTotalAttendSalary() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TotalAttendSalary
        var returnedTotalAttendSalary = om.readValue(
            restTotalAttendSalaryMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(totalAttendSalary)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TotalAttendSalary.class
        );

        // Validate the TotalAttendSalary in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertTotalAttendSalaryUpdatableFieldsEquals(returnedTotalAttendSalary, getPersistedTotalAttendSalary(returnedTotalAttendSalary));

        insertedTotalAttendSalary = returnedTotalAttendSalary;
    }

    @Test
    @Transactional
    void createTotalAttendSalaryWithExistingId() throws Exception {
        // Create the TotalAttendSalary with an existing ID
        totalAttendSalary.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTotalAttendSalaryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(totalAttendSalary)))
            .andExpect(status().isBadRequest());

        // Validate the TotalAttendSalary in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTotalAttendSalaries() throws Exception {
        // Initialize the database
        insertedTotalAttendSalary = totalAttendSalaryRepository.saveAndFlush(totalAttendSalary);

        // Get all the totalAttendSalaryList
        restTotalAttendSalaryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(totalAttendSalary.getId().intValue())))
            .andExpect(jsonPath("$.[*].totalAttend").value(hasItem(DEFAULT_TOTAL_ATTEND)))
            .andExpect(jsonPath("$.[*].totalSalary").value(hasItem(DEFAULT_TOTAL_SALARY)));
    }

    @Test
    @Transactional
    void getTotalAttendSalary() throws Exception {
        // Initialize the database
        insertedTotalAttendSalary = totalAttendSalaryRepository.saveAndFlush(totalAttendSalary);

        // Get the totalAttendSalary
        restTotalAttendSalaryMockMvc
            .perform(get(ENTITY_API_URL_ID, totalAttendSalary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(totalAttendSalary.getId().intValue()))
            .andExpect(jsonPath("$.totalAttend").value(DEFAULT_TOTAL_ATTEND))
            .andExpect(jsonPath("$.totalSalary").value(DEFAULT_TOTAL_SALARY));
    }

    @Test
    @Transactional
    void getNonExistingTotalAttendSalary() throws Exception {
        // Get the totalAttendSalary
        restTotalAttendSalaryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTotalAttendSalary() throws Exception {
        // Initialize the database
        insertedTotalAttendSalary = totalAttendSalaryRepository.saveAndFlush(totalAttendSalary);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the totalAttendSalary
        TotalAttendSalary updatedTotalAttendSalary = totalAttendSalaryRepository.findById(totalAttendSalary.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTotalAttendSalary are not directly saved in db
        em.detach(updatedTotalAttendSalary);
        updatedTotalAttendSalary.totalAttend(UPDATED_TOTAL_ATTEND).totalSalary(UPDATED_TOTAL_SALARY);

        restTotalAttendSalaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTotalAttendSalary.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedTotalAttendSalary))
            )
            .andExpect(status().isOk());

        // Validate the TotalAttendSalary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTotalAttendSalaryToMatchAllProperties(updatedTotalAttendSalary);
    }

    @Test
    @Transactional
    void putNonExistingTotalAttendSalary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        totalAttendSalary.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTotalAttendSalaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, totalAttendSalary.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(totalAttendSalary))
            )
            .andExpect(status().isBadRequest());

        // Validate the TotalAttendSalary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTotalAttendSalary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        totalAttendSalary.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTotalAttendSalaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(totalAttendSalary))
            )
            .andExpect(status().isBadRequest());

        // Validate the TotalAttendSalary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTotalAttendSalary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        totalAttendSalary.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTotalAttendSalaryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(totalAttendSalary)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TotalAttendSalary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTotalAttendSalaryWithPatch() throws Exception {
        // Initialize the database
        insertedTotalAttendSalary = totalAttendSalaryRepository.saveAndFlush(totalAttendSalary);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the totalAttendSalary using partial update
        TotalAttendSalary partialUpdatedTotalAttendSalary = new TotalAttendSalary();
        partialUpdatedTotalAttendSalary.setId(totalAttendSalary.getId());

        restTotalAttendSalaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTotalAttendSalary.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTotalAttendSalary))
            )
            .andExpect(status().isOk());

        // Validate the TotalAttendSalary in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTotalAttendSalaryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTotalAttendSalary, totalAttendSalary),
            getPersistedTotalAttendSalary(totalAttendSalary)
        );
    }

    @Test
    @Transactional
    void fullUpdateTotalAttendSalaryWithPatch() throws Exception {
        // Initialize the database
        insertedTotalAttendSalary = totalAttendSalaryRepository.saveAndFlush(totalAttendSalary);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the totalAttendSalary using partial update
        TotalAttendSalary partialUpdatedTotalAttendSalary = new TotalAttendSalary();
        partialUpdatedTotalAttendSalary.setId(totalAttendSalary.getId());

        partialUpdatedTotalAttendSalary.totalAttend(UPDATED_TOTAL_ATTEND).totalSalary(UPDATED_TOTAL_SALARY);

        restTotalAttendSalaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTotalAttendSalary.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTotalAttendSalary))
            )
            .andExpect(status().isOk());

        // Validate the TotalAttendSalary in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTotalAttendSalaryUpdatableFieldsEquals(
            partialUpdatedTotalAttendSalary,
            getPersistedTotalAttendSalary(partialUpdatedTotalAttendSalary)
        );
    }

    @Test
    @Transactional
    void patchNonExistingTotalAttendSalary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        totalAttendSalary.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTotalAttendSalaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, totalAttendSalary.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(totalAttendSalary))
            )
            .andExpect(status().isBadRequest());

        // Validate the TotalAttendSalary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTotalAttendSalary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        totalAttendSalary.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTotalAttendSalaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(totalAttendSalary))
            )
            .andExpect(status().isBadRequest());

        // Validate the TotalAttendSalary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTotalAttendSalary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        totalAttendSalary.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTotalAttendSalaryMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(totalAttendSalary)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TotalAttendSalary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTotalAttendSalary() throws Exception {
        // Initialize the database
        insertedTotalAttendSalary = totalAttendSalaryRepository.saveAndFlush(totalAttendSalary);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the totalAttendSalary
        restTotalAttendSalaryMockMvc
            .perform(delete(ENTITY_API_URL_ID, totalAttendSalary.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return totalAttendSalaryRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected TotalAttendSalary getPersistedTotalAttendSalary(TotalAttendSalary totalAttendSalary) {
        return totalAttendSalaryRepository.findById(totalAttendSalary.getId()).orElseThrow();
    }

    protected void assertPersistedTotalAttendSalaryToMatchAllProperties(TotalAttendSalary expectedTotalAttendSalary) {
        assertTotalAttendSalaryAllPropertiesEquals(expectedTotalAttendSalary, getPersistedTotalAttendSalary(expectedTotalAttendSalary));
    }

    protected void assertPersistedTotalAttendSalaryToMatchUpdatableProperties(TotalAttendSalary expectedTotalAttendSalary) {
        assertTotalAttendSalaryAllUpdatablePropertiesEquals(
            expectedTotalAttendSalary,
            getPersistedTotalAttendSalary(expectedTotalAttendSalary)
        );
    }
}
