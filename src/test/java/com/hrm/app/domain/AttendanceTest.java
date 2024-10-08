package com.hrm.app.domain;

import static com.hrm.app.domain.AttendanceTestSamples.*;
import static com.hrm.app.domain.EmployeeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.hrm.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AttendanceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Attendance.class);
        Attendance attendance1 = getAttendanceSample1();
        Attendance attendance2 = new Attendance();
        assertThat(attendance1).isNotEqualTo(attendance2);

        attendance2.setId(attendance1.getId());
        assertThat(attendance1).isEqualTo(attendance2);

        attendance2 = getAttendanceSample2();
        assertThat(attendance1).isNotEqualTo(attendance2);
    }

    @Test
    void employeeTest() {
        Attendance attendance = getAttendanceRandomSampleGenerator();
        Employee employeeBack = getEmployeeRandomSampleGenerator();

        attendance.setEmployee(employeeBack);
        assertThat(attendance.getEmployee()).isEqualTo(employeeBack);

        attendance.employee(null);
        assertThat(attendance.getEmployee()).isNull();
    }
}
