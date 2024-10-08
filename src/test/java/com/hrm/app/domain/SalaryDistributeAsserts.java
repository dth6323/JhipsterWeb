package com.hrm.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class SalaryDistributeAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSalaryDistributeAllPropertiesEquals(SalaryDistribute expected, SalaryDistribute actual) {
        assertSalaryDistributeAutoGeneratedPropertiesEquals(expected, actual);
        assertSalaryDistributeAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSalaryDistributeAllUpdatablePropertiesEquals(SalaryDistribute expected, SalaryDistribute actual) {
        assertSalaryDistributeUpdatableFieldsEquals(expected, actual);
        assertSalaryDistributeUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSalaryDistributeAutoGeneratedPropertiesEquals(SalaryDistribute expected, SalaryDistribute actual) {
        assertThat(expected)
            .as("Verify SalaryDistribute auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSalaryDistributeUpdatableFieldsEquals(SalaryDistribute expected, SalaryDistribute actual) {
        assertThat(expected)
            .as("Verify SalaryDistribute relevant properties")
            .satisfies(e -> assertThat(e.getStartDate()).as("check startDate").isEqualTo(actual.getStartDate()))
            .satisfies(e -> assertThat(e.getEndDate()).as("check endDate").isEqualTo(actual.getEndDate()))
            .satisfies(e -> assertThat(e.getWorkDay()).as("check workDay").isEqualTo(actual.getWorkDay()))
            .satisfies(e -> assertThat(e.getTypeOfSalary()).as("check typeOfSalary").isEqualTo(actual.getTypeOfSalary()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSalaryDistributeUpdatableRelationshipsEquals(SalaryDistribute expected, SalaryDistribute actual) {
        // empty method
    }
}
