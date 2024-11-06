package com.hrm.app.service.impl;

import com.hrm.app.domain.TotalAttendSalary;
import com.hrm.app.repository.TotalAttendSalaryRepository;
import com.hrm.app.service.TotalAttendSalaryService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.hrm.app.domain.TotalAttendSalary}.
 */
@Service
@Transactional
public class TotalAttendSalaryServiceImpl implements TotalAttendSalaryService {

    private static final Logger LOG = LoggerFactory.getLogger(TotalAttendSalaryServiceImpl.class);
    private final TotalAttendSalaryRepository totalAttendSalaryRepository;

    public TotalAttendSalaryServiceImpl(TotalAttendSalaryRepository totalAttendSalaryRepository) {
        this.totalAttendSalaryRepository = totalAttendSalaryRepository;
    }

    @Override
    public TotalAttendSalary save(TotalAttendSalary totalAttendSalary) {
        LOG.debug("Request to save TotalAttendSalary : {}", totalAttendSalary);
        return totalAttendSalaryRepository.save(totalAttendSalary);
    }

    @Override
    public TotalAttendSalary update(TotalAttendSalary totalAttendSalary) {
        LOG.debug("Request to update TotalAttendSalary : {}", totalAttendSalary);
        return totalAttendSalaryRepository.save(totalAttendSalary);
    }

    @Override
    public Optional<TotalAttendSalary> partialUpdate(TotalAttendSalary totalAttendSalary) {
        LOG.debug("Request to partially update TotalAttendSalary : {}", totalAttendSalary);

        return totalAttendSalaryRepository
            .findById(totalAttendSalary.getId())
            .map(existingTotalAttendSalary -> {
                if (totalAttendSalary.getTotalAttend() != null) {
                    existingTotalAttendSalary.setTotalAttend(totalAttendSalary.getTotalAttend());
                }
                if (totalAttendSalary.getTotalSalary() != null) {
                    existingTotalAttendSalary.setTotalSalary(totalAttendSalary.getTotalSalary());
                }

                return existingTotalAttendSalary;
            })
            .map(totalAttendSalaryRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TotalAttendSalary> findAll() {
        LOG.debug("Request to get all TotalAttendSalaries");
        return totalAttendSalaryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TotalAttendSalary> findOne(Long id) {
        LOG.debug("Request to get TotalAttendSalary : {}", id);
        return totalAttendSalaryRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete TotalAttendSalary : {}", id);
        totalAttendSalaryRepository.deleteById(id);
    }
}
