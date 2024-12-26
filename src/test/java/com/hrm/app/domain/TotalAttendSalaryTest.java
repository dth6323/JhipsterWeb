package com.hrm.app.domain;

import static com.hrm.app.domain.EmployeeTestSamples.*;
import static com.hrm.app.domain.TotalAttendSalaryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.hrm.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TotalAttendSalaryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TotalAttendSalary.class);
        TotalAttendSalary totalAttendSalary1 = getTotalAttendSalarySample1();
        TotalAttendSalary totalAttendSalary2 = new TotalAttendSalary();
        assertThat(totalAttendSalary1).isNotEqualTo(totalAttendSalary2);

        totalAttendSalary2.setId(totalAttendSalary1.getId());
        assertThat(totalAttendSalary1).isEqualTo(totalAttendSalary2);

        totalAttendSalary2 = getTotalAttendSalarySample2();
        assertThat(totalAttendSalary1).isNotEqualTo(totalAttendSalary2);
    }

    @Test
    void employeeTest() {
        TotalAttendSalary totalAttendSalary = getTotalAttendSalaryRandomSampleGenerator();
        Employee employeeBack = getEmployeeRandomSampleGenerator();

        totalAttendSalary.setEmployee(employeeBack);
        assertThat(totalAttendSalary.getEmployee()).isEqualTo(employeeBack);

        totalAttendSalary.employee(null);
        assertThat(totalAttendSalary.getEmployee()).isNull();
    }
}
