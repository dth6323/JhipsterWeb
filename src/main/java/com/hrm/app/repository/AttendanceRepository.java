package com.hrm.app.repository;

import com.hrm.app.domain.Attendance;
import com.hrm.app.service.dto.EmployeeAttendDTO;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Attendance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    @Query(
        "SELECT new com.hrm.app.service.dto.EmployeeAttendDTO(e.id, e.name,e.phone,e.email,e.address,e.gender,e.dateOfBirth, COUNT(a.id)) " +
        "FROM Attendance a RIGHT join a.employee e " +
        "GROUP BY e.id, e.name, e.phone, e.email, e.address, e.gender, e.dateOfBirth"
    )
    List<EmployeeAttendDTO> findAttendanceCountByEmployee();
}
