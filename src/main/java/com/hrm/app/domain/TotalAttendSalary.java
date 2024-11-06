package com.hrm.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A TotalAttendSalary.
 */
@Entity
@Table(name = "total_attend_salary")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TotalAttendSalary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "total_attend")
    private Integer totalAttend;

    @Column(name = "total_salary")
    private Integer totalSalary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "department", "contract", "attendances", "payrolls", "totalAttendSalaries" }, allowSetters = true)
    private Employee employee;

    public TotalAttendSalary() {}

    public TotalAttendSalary(Long id, Integer totalAttend, Integer totalSalary, Employee employee) {
        this.id = id;
        this.totalAttend = totalAttend;
        this.totalSalary = totalSalary;
        this.employee = employee;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TotalAttendSalary id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalAttend() {
        return this.totalAttend;
    }

    public TotalAttendSalary totalAttend(Integer totalAttend) {
        this.setTotalAttend(totalAttend);
        return this;
    }

    public void setTotalAttend(Integer totalAttend) {
        this.totalAttend = totalAttend;
    }

    public Integer getTotalSalary() {
        return this.totalSalary;
    }

    public TotalAttendSalary totalSalary(Integer totalSalary) {
        this.setTotalSalary(totalSalary);
        return this;
    }

    public void setTotalSalary(Integer totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TotalAttendSalary employee(Employee employee) {
        this.setEmployee(employee);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TotalAttendSalary)) {
            return false;
        }
        return getId() != null && getId().equals(((TotalAttendSalary) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TotalAttendSalary{" +
            "id=" + getId() +
            ", totalAttend=" + getTotalAttend() +
            ", totalSalary=" + getTotalSalary() +
            "}";
    }
}
