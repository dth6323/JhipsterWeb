package com.hrm.app.domain;

import static com.hrm.app.domain.AttendanceTestSamples.*;
import static com.hrm.app.domain.ContractTestSamples.*;
import static com.hrm.app.domain.DepartmentTestSamples.*;
import static com.hrm.app.domain.EmployeeTestSamples.*;
import static com.hrm.app.domain.PayrollTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.hrm.app.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Employee.class);
        Employee employee1 = getEmployeeSample1();
        Employee employee2 = new Employee();
        assertThat(employee1).isNotEqualTo(employee2);

        employee2.setId(employee1.getId());
        assertThat(employee1).isEqualTo(employee2);

        employee2 = getEmployeeSample2();
        assertThat(employee1).isNotEqualTo(employee2);
    }

    @Test
    void departmentTest() {
        Employee employee = getEmployeeRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        employee.setDepartment(departmentBack);
        assertThat(employee.getDepartment()).isEqualTo(departmentBack);

        employee.department(null);
        assertThat(employee.getDepartment()).isNull();
    }

    @Test
    void contractTest() {
        Employee employee = getEmployeeRandomSampleGenerator();
        Contract contractBack = getContractRandomSampleGenerator();

        employee.setContract(contractBack);
        assertThat(employee.getContract()).isEqualTo(contractBack);

        employee.contract(null);
        assertThat(employee.getContract()).isNull();
    }

    @Test
    void attendanceTest() {
        Employee employee = getEmployeeRandomSampleGenerator();
        Attendance attendanceBack = getAttendanceRandomSampleGenerator();

        employee.addAttendance(attendanceBack);
        assertThat(employee.getAttendances()).containsOnly(attendanceBack);
        assertThat(attendanceBack.getEmployee()).isEqualTo(employee);

        employee.removeAttendance(attendanceBack);
        assertThat(employee.getAttendances()).doesNotContain(attendanceBack);
        assertThat(attendanceBack.getEmployee()).isNull();

        employee.attendances(new HashSet<>(Set.of(attendanceBack)));
        assertThat(employee.getAttendances()).containsOnly(attendanceBack);
        assertThat(attendanceBack.getEmployee()).isEqualTo(employee);

        employee.setAttendances(new HashSet<>());
        assertThat(employee.getAttendances()).doesNotContain(attendanceBack);
        assertThat(attendanceBack.getEmployee()).isNull();
    }

    @Test
    void payrollTest() {
        Employee employee = getEmployeeRandomSampleGenerator();
        Payroll payrollBack = getPayrollRandomSampleGenerator();

        employee.addPayroll(payrollBack);
        assertThat(employee.getPayrolls()).containsOnly(payrollBack);
        assertThat(payrollBack.getEmployee()).isEqualTo(employee);

        employee.removePayroll(payrollBack);
        assertThat(employee.getPayrolls()).doesNotContain(payrollBack);
        assertThat(payrollBack.getEmployee()).isNull();

        employee.payrolls(new HashSet<>(Set.of(payrollBack)));
        assertThat(employee.getPayrolls()).containsOnly(payrollBack);
        assertThat(payrollBack.getEmployee()).isEqualTo(employee);

        employee.setPayrolls(new HashSet<>());
        assertThat(employee.getPayrolls()).doesNotContain(payrollBack);
        assertThat(payrollBack.getEmployee()).isNull();
    }
}
