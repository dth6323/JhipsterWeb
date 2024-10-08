package com.hrm.app.domain;

import static com.hrm.app.domain.ContractTestSamples.*;
import static com.hrm.app.domain.EmployeeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.hrm.app.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ContractTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contract.class);
        Contract contract1 = getContractSample1();
        Contract contract2 = new Contract();
        assertThat(contract1).isNotEqualTo(contract2);

        contract2.setId(contract1.getId());
        assertThat(contract1).isEqualTo(contract2);

        contract2 = getContractSample2();
        assertThat(contract1).isNotEqualTo(contract2);
    }

    @Test
    void employeeTest() {
        Contract contract = getContractRandomSampleGenerator();
        Employee employeeBack = getEmployeeRandomSampleGenerator();

        contract.addEmployee(employeeBack);
        assertThat(contract.getEmployees()).containsOnly(employeeBack);
        assertThat(employeeBack.getContract()).isEqualTo(contract);

        contract.removeEmployee(employeeBack);
        assertThat(contract.getEmployees()).doesNotContain(employeeBack);
        assertThat(employeeBack.getContract()).isNull();

        contract.employees(new HashSet<>(Set.of(employeeBack)));
        assertThat(contract.getEmployees()).containsOnly(employeeBack);
        assertThat(employeeBack.getContract()).isEqualTo(contract);

        contract.setEmployees(new HashSet<>());
        assertThat(contract.getEmployees()).doesNotContain(employeeBack);
        assertThat(employeeBack.getContract()).isNull();
    }
}
