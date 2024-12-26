package com.hrm.app.service;

import com.hrm.app.domain.Employee;
import com.hrm.app.repository.AttendanceRepository;
import com.hrm.app.repository.EmployeeRepository;
import com.hrm.app.service.dto.EmployeeAttendDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private AttendanceRepository attendanceRepository;

    public EmployeeService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }
    //    public List<EmployeeAttendDTO> getall(){
    //        return attendanceRepository.findAttendanceCountByEmployee();
    //    }
}
