package com.hrm.app.service;

import com.hrm.app.domain.TotalAttendSalary;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.hrm.app.domain.TotalAttendSalary}.
 */
public interface TotalAttendSalaryService {
    /**
     * Save a totalAttendSalary.
     *
     * @param totalAttendSalary the entity to save.
     * @return the persisted entity.
     */
    TotalAttendSalary save(TotalAttendSalary totalAttendSalary);

    /**
     * Updates a totalAttendSalary.
     *
     * @param totalAttendSalary the entity to update.
     * @return the persisted entity.
     */
    TotalAttendSalary update(TotalAttendSalary totalAttendSalary);

    /**
     * Partially updates a totalAttendSalary.
     *
     * @param totalAttendSalary the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TotalAttendSalary> partialUpdate(TotalAttendSalary totalAttendSalary);

    /**
     * Get all the totalAttendSalaries.
     *
     * @return the list of entities.
     */
    List<TotalAttendSalary> findAll();

    /**
     * Get the "id" totalAttendSalary.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TotalAttendSalary> findOne(Long id);

    /**
     * Delete the "id" totalAttendSalary.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
