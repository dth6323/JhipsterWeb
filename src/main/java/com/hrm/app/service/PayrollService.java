package com.hrm.app.service;

import com.hrm.app.repository.PayrollRepository;
import com.hrm.app.service.dto.EmployeeAttendDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Transactional
public class PayrollService {

    private PayrollRepository payrollRepository;

    public PayrollService(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    public List<EmployeeAttendDTO> getEmployeeSalaryAndAttendance(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            payrollRepository.getall();
        }
        List<Object[]> results = payrollRepository.getEmployeeSalaryAndAttendance(startDate, endDate);

        return results
            .stream()
            .map(result -> {
                Long id = (Long) result[0]; // ID nhân viên
                String name = (String) result[1]; // Tên nhân viên
                Float baseSalary = (Float) result[2]; // Lương cơ bản
                Long attendanceDays = (Long) result[3]; // Số ngày đi làm

                Float totalSalary = baseSalary * attendanceDays;

                return new EmployeeAttendDTO(id, name, baseSalary, attendanceDays, totalSalary);
            })
            .collect(Collectors.toList());
    }
}
