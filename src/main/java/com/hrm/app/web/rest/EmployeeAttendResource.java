package com.hrm.app.web.rest;

import com.hrm.app.service.EmployeeService;
import com.hrm.app.service.dto.EmployeeAttendDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ea")
@Transactional
public class EmployeeAttendResource {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeAttendResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    //@GetMapping("")
    //    public ResponseEntity<List<EmployeeAttendDTO>> getAttendanceCount() {
    //        return ResponseEntity.ok(employeeService.getall()); // Gọi phương thức trong service
    //    }
}
