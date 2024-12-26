package com.hrm.app.service.dto;

import java.math.BigDecimal;

public class SalaryDistributeDTO {

    private String employeeName;
    private BigDecimal wageCoefficients;
    private BigDecimal wageBaseSalary;
    private BigDecimal wageAllowance;
    private Long totalWorkDays;
    private BigDecimal totalSalary;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public BigDecimal getWageCoefficients() {
        return wageCoefficients;
    }

    public void setWageCoefficients(BigDecimal wageCoefficients) {
        this.wageCoefficients = wageCoefficients;
    }

    public BigDecimal getWageBaseSalary() {
        return wageBaseSalary;
    }

    public void setWageBaseSalary(BigDecimal wageBaseSalary) {
        this.wageBaseSalary = wageBaseSalary;
    }

    public BigDecimal getWageAllowance() {
        return wageAllowance;
    }

    public void setWageAllowance(BigDecimal wageAllowance) {
        this.wageAllowance = wageAllowance;
    }

    public Long getTotalWorkDays() {
        return totalWorkDays;
    }

    public void setTotalWorkDays(Long totalWorkDays) {
        this.totalWorkDays = totalWorkDays;
    }

    public BigDecimal getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = totalSalary;
    }

    // Constructor
    public SalaryDistributeDTO(
        String employeeName,
        BigDecimal wageCoefficients,
        BigDecimal wageBaseSalary,
        BigDecimal wageAllowance,
        Long totalWorkDays,
        BigDecimal totalSalary
    ) {
        this.employeeName = employeeName;
        this.wageCoefficients = wageCoefficients;
        this.wageBaseSalary = wageBaseSalary;
        this.wageAllowance = wageAllowance;
        this.totalWorkDays = totalWorkDays;
        this.totalSalary = totalSalary;
    }
}
