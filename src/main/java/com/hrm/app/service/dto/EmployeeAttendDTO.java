package com.hrm.app.service.dto;

import com.hrm.app.domain.Employee;
import java.time.LocalDate;

public class EmployeeAttendDTO {

    private Long id;
    private String name;
    private Float baseSalary;
    private Long attendanceDays;
    private Float totalSalary;

    private Float calculateTotalSalary() {
        return baseSalary * attendanceDays;
    }

    public EmployeeAttendDTO(Long id, String name, Float baseSalary, Long attendanceDays, Float totalSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
        this.attendanceDays = attendanceDays;
        this.totalSalary = totalSalary;
    }

    public EmployeeAttendDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Float baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Long getAttendanceDays() {
        return attendanceDays;
    }

    public void setAttendanceDays(Long attendanceDays) {
        this.attendanceDays = attendanceDays;
    }

    public Float getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Float totalSalary) {
        this.totalSalary = totalSalary;
    }
}
