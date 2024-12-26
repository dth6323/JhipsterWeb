package com.hrm.app.service.mapper;

import com.hrm.app.service.dto.SalaryDistributeDTO;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SalaryDistributeMapper {

    public SalaryDistributeDTO toDto(Object[] result) {
        return new SalaryDistributeDTO(
            (String) result[0], // employeeName
            (BigDecimal) result[1], // wageCoefficients
            (BigDecimal) result[2], // wageBaseSalary
            (BigDecimal) result[3], // wageAllowance
            ((Number) result[4]).longValue(), // totalWorkDays
            (BigDecimal) result[5] // totalSalary
        );
    }

    public List<SalaryDistributeDTO> toDtoList(List<Object[]> results) {
        return results.stream().map(this::toDto).collect(Collectors.toList());
    }
}
